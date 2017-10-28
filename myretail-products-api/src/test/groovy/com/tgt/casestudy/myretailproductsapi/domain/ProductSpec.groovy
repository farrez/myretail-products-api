package com.tgt.casestudy.myretailproductsapi.domain

import com.fasterxml.jackson.annotation.JsonInclude
import spock.lang.Specification

class ProductSpec extends Specification {

    def 'spring annotations'(){
        expect:
        Product.isAnnotationPresent(JsonInclude)
        Product.getAnnotation(JsonInclude).value() == JsonInclude.Include.NON_NULL
    }
}
