package com.tgt.casestudy.myretailproductsapi.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.cassandra.core.Ordering
import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table
import spock.lang.Specification

class PriceSpec extends Specification {

    def 'annotations and field declarations'() {
        expect:
        Price.isAnnotationPresent(Table)
        Price.getAnnotation(Table).value() == 'product_price'
        Price.getDeclaredField('product_id').isAnnotationPresent(JsonIgnore)
        Price.getDeclaredField('product_id').isAnnotationPresent(PrimaryKeyColumn)
        Price.getDeclaredField('product_id').getAnnotation(PrimaryKeyColumn).name() == 'product_id'
        Price.getDeclaredField('product_id').getAnnotation(PrimaryKeyColumn).ordinal() == 0
        Price.getDeclaredField('product_id').getAnnotation(PrimaryKeyColumn).type() == PrimaryKeyType.PARTITIONED
        Price.getDeclaredField('product_id').getAnnotation(PrimaryKeyColumn).ordering() == Ordering.DESCENDING
        Price.getDeclaredField('value')
        Price.getDeclaredField('currency_code')
    }
}
