package com.tgt.casestudy.myretailproductsapi.fta

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyretailProductsApiFta extends Specification {

    @Autowired
    TestRestTemplate restTemplate

    def "get product"() {
        when:
        String response = restTemplate.getForObject("/products/${productId}", String)

        then:
        response == expectedJson

        where:
        productId  | expectedJson
        '13860428' | '''{"id":13860428,"name":"The Big Lebowski (Blu-ray)"}'''
        '15643793' | '''{"id":15643793}'''
        '16752456' | '''{"id":16752456}'''
        '16696652' | '''{"id":16696652,"name":"Beats Solo 2 Wireless - Black"}'''
        '16483589' | '''{"id":16483589}'''
        '15117729' | '''{"id":15117729}'''
    }
}
