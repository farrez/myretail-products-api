package com.tgt.casestudy.myretailproductsapi.controller

import com.tgt.casestudy.myretailproductsapi.domain.Product
import com.tgt.casestudy.myretailproductsapi.service.ProductService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.RestController
import spock.lang.Specification

import javax.annotation.Resource

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ProductsApiControllerSpec extends Specification {

    ProductService mockProductService = Mock()
    ProductsApiController controller = new ProductsApiController(productService: mockProductService)

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build()

    def 'spring wiring is correct'() {
        expect:
        ProductsApiController.isAnnotationPresent(RestController)
        ProductsApiController.getDeclaredField('productService').getAnnotation(Resource)
    }

    def 'get product by id'() {
        setup:
        int id = 13860428

        when:
        String result = mockMvc.perform(get("/products/${id}"))
                .andExpect(status().isOk())
                .andExpect(header().string('Content-Type', 'application/json;charset=UTF-8'))
                .andReturn().response.contentAsString

        then:
        1 * mockProductService.getProduct(id) >> new Product(id: id, name: "Our Name")
        '''{"id":13860428,"name":"Our Name"}''' == result
    }
}
