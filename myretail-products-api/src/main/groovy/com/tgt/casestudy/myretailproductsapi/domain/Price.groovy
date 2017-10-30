package com.tgt.casestudy.myretailproductsapi.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import org.springframework.cassandra.core.Ordering
import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table

@Canonical
@Table(value = "product_price")
class Price {
    @JsonIgnore
    @PrimaryKeyColumn(name = "product_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
    int product_id
    double value
    @JsonProperty('currency_code')
    @Column('currency_code')
    String currencyCode
}
