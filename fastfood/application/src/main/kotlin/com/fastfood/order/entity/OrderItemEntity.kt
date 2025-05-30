package com.fastfood.order.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_order_items")
data class OrderItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: OrderEntity? = null,

    val productId: Long,
    val quantity: Long,
    val itemValue: Double
)
