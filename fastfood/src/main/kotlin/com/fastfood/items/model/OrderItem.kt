package com.fastfood.items.model

data class OrderItem(
    val productId: Long,
    val quantity: Long,
    val itemValue: Double
)