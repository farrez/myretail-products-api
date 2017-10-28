package com.tgt.casestudy.myretailproductsapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import spock.lang.Specification

class MyretailProductsApiApplicationSpec extends Specification {

    def 'spring wiring is correct'() {
        expect:
        MyretailProductsApiApplication.isAnnotationPresent(SpringBootApplication)
        MyretailProductsApiApplication.getDeclaredMethod('restTemplate').isAnnotationPresent(Bean)
    }
}
