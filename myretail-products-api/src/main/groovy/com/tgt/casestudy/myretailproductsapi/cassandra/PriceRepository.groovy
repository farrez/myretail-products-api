package com.tgt.casestudy.myretailproductsapi.cassandra

import com.tgt.casestudy.myretailproductsapi.domain.Price
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository

interface PriceRepository extends CrudRepository<Price, Integer> {
    @Query("select value, currency_code from product_price where product_id=?0")
    public Price getPrice(int id)
}