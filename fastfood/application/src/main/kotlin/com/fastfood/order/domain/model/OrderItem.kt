package com.fastfood.order.domain.model

data class OrderItem(
    val productId: Long,
    val quantity: Long,
    val itemValue: Double
)