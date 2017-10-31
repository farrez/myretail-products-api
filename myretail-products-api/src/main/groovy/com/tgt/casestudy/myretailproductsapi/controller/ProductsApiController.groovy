package com.tgt.casestudy.myretailproductsapi.controller

import com.tgt.casestudy.myretailproductsapi.domain.Product
import com.tgt.casestudy.myretailproductsapi.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.annotation.Resource

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.PUT

@RestController
class ProductsApiController {

    @Resource
    ProductService productService

    @RequestMapping(method = GET, path = '/products/{id}')
    public Product getProduct(@PathVariable int id) {
        return productService.getProduct(id)
    }

    @RequestMapping(method = PUT, path = '/products/{id}')
    public ResponseEntity<Product> saveProductPrice(@RequestBody Product product) {
        product.price.product_id = product.id
        productService.saveProductPrice(product.price)
        return new ResponseEntity(product, HttpStatus.CREATED)
    }
}


