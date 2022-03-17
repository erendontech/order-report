package com.order.report.model.paging;

class PagingRequest {
    var start: Long = 0L
    var length: Long = 0L
    var draw : Int = 0
    var order: List<Order>? = null
    var columns: List<Column>? = null
    var search = Search()
}
