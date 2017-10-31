package com.tgt.casestudy.myretailproductsapi.service

import com.tgt.casestudy.myretailproductsapi.cassandra.PriceRepository
import com.tgt.casestudy.myretailproductsapi.domain.Price
import com.tgt.casestudy.myretailproductsapi.domain.Product
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ProductService {

    Logger logger = LogManager.getLogger(getClass())

    @Autowired
    RestTemplate restTemplate

    @Autowired
    PriceRepository productRepository

    @Value('${myRetail.productUrl}')
    String myRetailProductUrl

    Product getProduct(int id) {
        return new Product(id: id, name: getProductName(id), price: productRepository.getPrice(id))
    }

    String getProductName(int id) {
        Map myRetailProductData = [:]
        try {
            myRetailProductData = restTemplate.getForObject(myRetailProductUrl, Map, [id: id])
        } catch (Throwable t) {
            logger.warn("Error calling myRetail: '${t.message}'")
        }
        return myRetailProductData.product?.item?.product_description?.title ?: null
    }

    void saveProductPrice(Price price) {
        productRepository.save(price)
    }
}
