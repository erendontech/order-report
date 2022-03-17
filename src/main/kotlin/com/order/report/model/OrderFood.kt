package com.order.report.model;

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import java.util.Date

@Introspected
data class OrderFood(

    @JsonProperty("order_id")
    var orderId: Long = 0,

    @JsonProperty("id")
    var id: String = "",

    @JsonProperty("price")
    var price: Double = 0.0,

    @JsonProperty("total")
    var total: Double = 0.0,

    @JsonProperty("quantity")
    var quantity: Int = 0,

    @JsonProperty("name")
    var name: String = "",

    @JsonProperty("vendor_from_vendor")
    var vendor: String = "",

    @JsonProperty("delivery_start_from_order")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var deliveryStart: Date? = null,

    @JsonProperty("delivery_end_from_order")
    @JsonFormat(pattern = "yyyy-MM-dd")
    var deliveryEnd: Date? = null
)