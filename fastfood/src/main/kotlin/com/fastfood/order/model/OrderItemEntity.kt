package com.fastfood.order.model

import com.fastfood.product.model.ProductEntity
import jakarta.persistence.*

@Entity
@Table(name = "tb_order_items")
data class OrderItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    val order: OrderEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    val product: ProductEntity,

    val quantity: Long,

    val itemValue: Double
)
