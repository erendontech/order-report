package com.order.report.requestors

import com.fasterxml.jackson.databind.ObjectMapper
import com.order.report.model.OrderFood
import jakarta.inject.Singleton
import java.net.URI
import java.net.http.HttpClient;
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

@Singleton
class OrderCoreRequestor(
    private val objectMapper: ObjectMapper
) {

    fun getAllOrders(): List<OrderFood> {

        val httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build()

        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://localhost:8081/api/ms/order-core/order/get-all"))
            .build()

        val response: HttpResponse<String>? = httpClient.send(request, HttpResponse.BodyHandlers.ofString())

        val listType = objectMapper.typeFactory.constructCollectionType(ArrayList::class.java, OrderFood::class.java)

        return objectMapper.readValue(response?.body(), listType)
    }
}