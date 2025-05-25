package com.fastfood.product.model

import jakarta.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,
    val description: String, // <- deve estar presente
    val price: Double,
    var amount: Long,
    val category: String // <- deve estar presente
)
