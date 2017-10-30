package com.tgt.casestudy.myretailproductsapi.cassandra

import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository
import spock.lang.Specification

class PriceRepositorySpec extends Specification {

    def 'repository object is declared correctly'() {
        expect:
        PriceRepository in CrudRepository
        PriceRepository.getDeclaredMethod('getPrice', int).isAnnotationPresent(Query)
        PriceRepository.getDeclaredMethod('getPrice', int).getAnnotation(Query).value() == 'select value, currency_code from tgt_casestudy_pricing.product_price where product_id=?0'
    }
}
