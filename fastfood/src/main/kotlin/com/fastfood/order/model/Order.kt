package com.fastfood.order.model

import java.time.LocalDateTime

data class Order(
    val id: Long? = null,
    val clientId: Long? = null,
    val dateOrder: LocalDateTime = LocalDateTime.now(),
    val orderItems: List<OrderItem> = emptyList(),
    val discountValue: Double = 0.0,
    val totalValue: Double = 0.0,
    val status: OrderStatus = OrderStatus.PENDING
)

data class OrderItem(
    val productId: Long,
    val quantity: Long,
    val itemValue: Double
)