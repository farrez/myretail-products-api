package com.tgt.casestudy.myretailproductsapi.fta

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate

import javax.annotation.Resource

@ContextConfiguration(classes = [MyretailProductsApiFta])
@ComponentScan(value = 'com.tgt.casestudy.myretailproductsapi')
class MyretailProductsApiFta extends Specification {

    static {
        System.setProperty('app.config', 'src/main/resources')
    }

    @Resource
    RestTemplate restTemplate

    def setup() {
        new MyretailProductsApiApplication()
                .configure(new SpringApplicationBuilder(MyretailProductsApiApplication))
                .run()
    }

    def "get product"() {
        when:
        String response = restTemplate.getForObject('http://localhost/products/13860428', String)

        then:
        response.status == 200
        '''{"id":13860428,"name":"The Big Lebowski (Blu-ray)"}'''
    }
}
