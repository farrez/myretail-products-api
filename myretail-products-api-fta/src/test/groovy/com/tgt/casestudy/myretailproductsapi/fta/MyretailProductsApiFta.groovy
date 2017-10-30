package com.tgt.casestudy.myretailproductsapi.fta

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyretailProductsApiFta extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    @Unroll
    def "get product with name and price"() {
        when:
        String response = restTemplate.getForObject("/products/${productId}", String)

        then:
        response == expectedJson

        where:
        productId  | expectedJson
        '13860428' | '''{"id":13860428,"name":"The Big Lebowski (Blu-ray)","current_price":{"value":13.49,"currency_code":"USD"}}'''
        '15643793' | '''{"id":15643793,"current_price":{"value":18.99,"currency_code":"USD"}}'''
        '16752456' | '''{"id":16752456,"current_price":{"value":17.99,"currency_code":"USD"}}'''
        '16696652' | '''{"id":16696652,"name":"Beats Solo 2 Wireless - Black","current_price":{"value":16.99,"currency_code":"USD"}}'''
        '16483589' | '''{"id":16483589,"current_price":{"value":15.99,"currency_code":"USD"}}'''
        '15117729' | '''{"id":15117729,"current_price":{"value":14.99,"currency_code":"USD"}}'''
    }
}
