package com.order.report.controller

import com.order.report.model.OrderFood
import com.order.report.model.paging.Page
import com.order.report.model.paging.PagingRequest
import com.order.report.service.OrderFoodService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.rxjava3.core.Single

@Controller("/ordersFood")
class OrderFoodController(
    private val orderFoodService: OrderFoodService
) {

    @Post("/")
    fun list(@Body pagingRequest: PagingRequest): Page<OrderFood> {

        return orderFoodService.getOrders(pagingRequest)
    }
}