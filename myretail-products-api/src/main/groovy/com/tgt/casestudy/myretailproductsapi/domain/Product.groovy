package com.tgt.casestudy.myretailproductsapi.domain

import groovy.transform.Canonical
import org.springframework.stereotype.Component

@Canonical
class Product {
    int id
    String name
}

