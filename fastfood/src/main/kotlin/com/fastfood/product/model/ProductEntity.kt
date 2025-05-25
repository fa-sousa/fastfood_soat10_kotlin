package com.fastfood.product.model

import jakarta.persistence.*

@Entity
@Table(name = "tb_products")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val price: Double,

    val amount: Long
)
