package com.fastfood.order.domain

import com.fastfood.items.model.OrderItem
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
