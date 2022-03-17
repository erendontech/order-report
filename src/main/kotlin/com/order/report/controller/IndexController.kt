package com.order.report.controller

import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View

@Controller("/")
class IndexController() {

    @View("index")
    @Get("/")
    fun showDatatable(): HttpResponse<BaseResult> {

        return HttpResponse.ok(BaseResult())
    }
}

@Introspected
class BaseResult(){
}
