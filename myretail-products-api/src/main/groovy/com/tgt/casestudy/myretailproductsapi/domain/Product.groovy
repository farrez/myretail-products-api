package com.tgt.casestudy.myretailproductsapi.domain

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
class Product {
    int id
    String name
}

