package com.tgt.casestudy.myretailproductsapi.controller

import com.tgt.casestudy.myretailproductsapi.domain.Price
import com.tgt.casestudy.myretailproductsapi.domain.Product
import com.tgt.casestudy.myretailproductsapi.service.ProductService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.RestController
import spock.lang.Specification

import javax.annotation.Resource

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
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

    def 'save product price'() {
        setup:
        int id = 13860428
        String expectedResult = '''{"id":13860428,"name":"The Big Lebowski (Blu-ray)","current_price":{"value":33.22,"currency_code":"CAD"}}'''
        when:
        String result = mockMvc.perform(put("/products/${id}").content(expectedResult).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().response.contentAsString

        then:
        1 * mockProductService.saveProductPrice(new Price(product_id: id, value: 33.22, currencyCode: 'CAD'))
        expectedResult == result
    }
}
