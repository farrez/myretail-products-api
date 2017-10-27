package com.tgt.casestudy.myretailproductsapi.service

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import com.tgt.casestudy.myretailproductsapi.domain.Product
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

import javax.annotation.Resource

@Component
class ProductService {

    Logger logger = LogManager.getLogger(getClass())

    @Resource
    RestTemplate restTemplate

    @Value('${myRetail.productUrl}')
    String myRetailProductUrl

    Product getProduct(int id) {
        Map myRetailProductData = [:]
        try {
            myRetailProductData =  restTemplate.getForObject(myRetailProductUrl, Map, [id: id])
        } catch (Throwable t) {
            logger.warn("Error calling myRetail: '${t.message}'")
        }
        String name = myRetailProductData.product?.item?.product_description?.title
        return new Product(id: id, name: name ?: null)
    }
}
