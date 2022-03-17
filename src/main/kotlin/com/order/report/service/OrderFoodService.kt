package com.order.report.service;

import com.order.report.model.OrderFood
import com.order.report.model.OrderFoodComparators
import com.order.report.model.paging.Order
import com.order.report.model.paging.Page
import com.order.report.model.paging.PagingRequest
import com.order.report.requestors.OrderCoreRequestor
import jakarta.inject.Singleton
import java.util.Calendar
import java.util.Locale
import java.util.function.Predicate
import java.util.stream.Collectors

@Singleton
class OrderFoodService(
    private val orderFoodComparators: OrderFoodComparators,
    private val orderCoreRequestor: OrderCoreRequestor
) {

    fun getOrders(pagingRequest: PagingRequest): Page<OrderFood> {
        val orders = orderCoreRequestor.getAllOrders().filter { it.deliveryEnd != null && it.deliveryStart != null }
        return getPage(orders, pagingRequest)
    }

    private fun getPage(orders: List<OrderFood>, pagingRequest: PagingRequest): Page<OrderFood> {
        val filtered: List<OrderFood> = orders.stream()
            .sorted(sortOrders(pagingRequest))
            .filter(filterOrdersFood(pagingRequest))
            .skip(pagingRequest.start)
            .limit(pagingRequest.length)
            .collect(Collectors.toList())
        val count = orders.stream()
            .filter(filterOrdersFood(pagingRequest))
            .count()
        val page = Page<OrderFood>().apply { data = filtered }
        page.recordsFiltered = count.toInt()
        page.recordsTotal = count.toInt()
        page.draw = pagingRequest.draw
        return page
    }

    private fun filterOrdersFood(pagingRequest: PagingRequest): Predicate<OrderFood>? {
        if (pagingRequest.search == null || pagingRequest.search.value.isNullOrEmpty()) {
            return Predicate<OrderFood> { true }
        }
        val value: String = pagingRequest.search.value!!
        return Predicate<OrderFood> { orderFood: OrderFood ->
            (orderFood.name
                .lowercase(Locale.getDefault())
                .contains(value)
                || orderFood.total.toString()
                .contains(value))
        }
    }


    private fun sortOrders(pagingRequest: PagingRequest): java.util.Comparator<OrderFood> {
        if (pagingRequest.order == null) {
            return Companion.EMPTY_COMPARATOR
        }
        try {
            val order: Order = pagingRequest.order!![0]
            val columnIndex: Int = order.column
            val column = pagingRequest.columns?.get(columnIndex)

            return orderFoodComparators.getComparator(column!!.data, order.dir)
                ?: return Companion.EMPTY_COMPARATOR
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return Companion.EMPTY_COMPARATOR
    }

    companion object {
        val EMPTY_COMPARATOR = Comparator<OrderFood> { _: OrderFood?, _: OrderFood? -> 0 }
    }
}
