package com.fastfood.product.model.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class ProductDTO(
    @field:NotBlank(message = "The attribute name can't be empty")
    val name: String,

    val description: String? = null,

    @field:NotNull(message = "The attribute price can't be null")
    @field:Positive(message = "The attribute price must be greater than zero")
    val price: Double,

    @field:NotNull(message = "The attribute amount can't be null")
    @field:Positive(message = "The attribute amount must be greater than zero")
    val amount: Long,

    @field:NotEmpty(message = "The attribute category can't be empty")
    val category: String
)
