package com.fastfood.order.controller

import com.fastfood.order.model.Order
import com.fastfood.order.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class OrderController(
    private val orderService: OrderService
) {

    @GetMapping
    fun getAllOrders(): ResponseEntity<List<Order>> =
        ResponseEntity.ok(orderService.getAllOrders())

    @PostMapping
    fun createOrder(@RequestBody request: CreateOrderRequest): ResponseEntity<Any> =
        try {
            val order = orderService.createOrder(request.clientId, request.items)
            ResponseEntity(order, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(e.message ?: "Erro ao criar pedido"), HttpStatus.BAD_REQUEST)
        }

    @PostMapping("/{orderId}/finalize")
    fun finalizeOrder(@PathVariable orderId: Long): ResponseEntity<Any> =
        try {
            val finalizedOrder = orderService.finalizeOrder(orderId)
            ResponseEntity.ok(finalizedOrder)
        } catch (e: Exception) {
            ResponseEntity(ErrorResponse(e.message ?: "Erro ao finalizar pedido"), HttpStatus.BAD_REQUEST)
        }

    data class CreateOrderRequest(
        val clientId: Long,
        val items: List<OrderService.OrderItemRequest>
    )

    data class ErrorResponse(val error: String)
}
