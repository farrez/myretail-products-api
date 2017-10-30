package com.tgt.casestudy.myretailproductsapi.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table
import spock.lang.Specification

class ProductSpec extends Specification {

    def 'spring annotations and field declarations'() {
        expect:
        Product.isAnnotationPresent(JsonInclude)
        Product.getAnnotation(JsonInclude).value() == JsonInclude.Include.NON_NULL
        Product.getDeclaredField('name')
        Product.getDeclaredField('price').isAnnotationPresent(JsonProperty)
        Product.getDeclaredField('price').getAnnotation(JsonProperty).value() == 'current_price'
    }
}
