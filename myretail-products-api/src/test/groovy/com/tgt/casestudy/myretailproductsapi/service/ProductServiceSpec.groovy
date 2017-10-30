package com.tgt.casestudy.myretailproductsapi.service

import com.tgt.casestudy.myretailproductsapi.cassandra.PriceRepository
import com.tgt.casestudy.myretailproductsapi.domain.Price
import com.tgt.casestudy.myretailproductsapi.domain.Product
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification
import spock.lang.Unroll

class ProductServiceSpec extends Specification {
    RestTemplate mockRestTemplate = Mock()
    PriceRepository mockProductRepository = Mock()
    Logger mockLogger = Mock()

    ProductService service = new ProductService(restTemplate: mockRestTemplate, myRetailProductUrl: "http://someurl/to/getproduct/{id}", logger: mockLogger, productRepository: mockProductRepository)

    def 'spring wiring is correct'() {
        expect:
        ProductService.isAnnotationPresent(Component)
        ProductService.getDeclaredField('restTemplate').getAnnotation(Autowired)
        ProductService.getDeclaredField('productRepository').getAnnotation(Autowired)
        ProductService.getDeclaredField('myRetailProductUrl').isAnnotationPresent(Value)
        ProductService.getDeclaredField('myRetailProductUrl').getAnnotation(Value).value() == '${myRetail.productUrl}'
    }

    @Unroll
    def 'get product calls myretail for name'() {
        when:
        Product result = service.getProduct(123)

        then:
        1 * mockRestTemplate.getForObject("http://someurl/to/getproduct/{id}", Map, [id: 123]) >> myRetailResult
        1 * mockProductRepository.getPrice(123) >> null
        expectedProduct == result

        where:
        expectedProduct                                          | myRetailResult
        new Product(id: 123, name: "The Big Lebowski (Blu-ray)") | [product: [item: [product_description: [title: "The Big Lebowski (Blu-ray)"]]]]
        new Product(id: 123)                                     | [product: [item: [product_description: [title: ""]]]]
        new Product(id: 123)                                     | [product: [item: [product_description: [title: null]]]]
        new Product(id: 123)                                     | [product: [item: [product_description: null]]]
        new Product(id: 123)                                     | [product: [item: null]]
        new Product(id: 123)                                     | [product: null]
        new Product(id: 123)                                     | [:]
    }

    @Unroll
    def 'get product calls product repository for price'() {
        when:
        Product result = service.getProduct(123)

        then:
        1 * mockRestTemplate.getForObject("http://someurl/to/getproduct/{id}", Map, [id: 123]) >> [:]
        1 * mockProductRepository.getPrice(123) >> price
        expectedProduct == result

        where:
        expectedProduct                                                            | price
        new Product(id: 123, price: new Price(value: 12.99, currencyCode: "USD")) | new Price(value: 12.99, currencyCode: "USD")
        new Product(id: 123, price: new Price(value: 12.99))                       | new Price(value: 12.99)
        new Product(id: 123, price: new Price(currencyCode: "USD"))               | new Price(currencyCode: "USD")
        new Product(id: 123)                                                       | null
    }

    @Unroll
    def 'handles myretail client exceptions'() {
        when:
        Product result = service.getProduct(123)

        then:
        1 * mockRestTemplate.getForObject("http://someurl/to/getproduct/{id}", Map, [id: 123]) >> { throw exception }
        1 * mockLogger.warn("Error calling myRetail: 'This is the message...'")
        notThrown(Exception)
        new Product(id: 123) == result

        where:
        exception << [new RestClientException("This is the message..."), new RuntimeException("This is the message...")]
    }
}
