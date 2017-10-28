package com.tgt.casestudy.myretailproductsapi.controller

import com.tgt.casestudy.myretailproductsapi.domain.Product
import com.tgt.casestudy.myretailproductsapi.service.ProductService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource

import static org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
class ProductsApiController {

    @Resource
    ProductService productService

    @RequestMapping(method =  GET, path = '/products/{id}')
    public Product getProduct(@PathVariable int id){
        return productService.getProduct(id)
    }
}


