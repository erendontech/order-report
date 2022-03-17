package com.order.report.model.paging;

class Column {

    var data: String? = null
    var name: String? = null
    var searchable: Boolean? = null
    var orderable: Boolean? = null
    var search: Search? = null

    fun Column(data: String) {
        this.data = data
    }
}



