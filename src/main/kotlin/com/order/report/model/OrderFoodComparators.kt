package com.order.report.model

import com.order.report.model.paging.Direction
import jakarta.inject.Singleton

@Singleton
class OrderFoodComparators {

    data class Key(
        var name: String? = null,
        var dir: Direction? = null
    )


    var map: MutableMap<Key, Comparator<OrderFood>> = initialize()

    private fun initialize(): MutableMap<Key, Comparator<OrderFood>> {
        val comparator = HashMap<Key, Comparator<OrderFood>>()

        comparator[Key("order_id", Direction.asc)] = Comparator.comparing(OrderFood::orderId)
        comparator[Key("order_id", Direction.desc)] = Comparator.comparing(OrderFood::orderId).reversed()

        comparator[Key("name", Direction.asc)] = Comparator.comparing(OrderFood::name)
        comparator[Key("name", Direction.desc)] = Comparator.comparing(OrderFood::name).reversed()

        comparator[Key("total", Direction.asc)] = Comparator.comparing(OrderFood::total)
        comparator[Key("total", Direction.desc)] = Comparator.comparing(OrderFood::total).reversed()

        comparator[Key("delivery_start_from_order", Direction.asc)] = Comparator.comparing(OrderFood::deliveryStart)
        comparator[Key("delivery_start_from_order", Direction.desc)] = Comparator.comparing(OrderFood::deliveryStart).reversed()

        comparator[Key("delivery_end_from_order", Direction.asc)] = Comparator.comparing(OrderFood::deliveryEnd)
        comparator[Key("delivery_end_from_order", Direction.desc)] = Comparator.comparing(OrderFood::deliveryEnd).reversed()

        return comparator
    }

    fun getComparator(name: String?, dir: Direction?): Comparator<OrderFood>? {
        return map[Key(name, dir)]
    }


}