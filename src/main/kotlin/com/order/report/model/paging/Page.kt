package com.order.report.model.paging;

class Page<T> {

    var data: List<T>? = null
    var recordsFiltered : Int = 0
    var recordsTotal : Int = 0
    var draw : Int = 0

}
