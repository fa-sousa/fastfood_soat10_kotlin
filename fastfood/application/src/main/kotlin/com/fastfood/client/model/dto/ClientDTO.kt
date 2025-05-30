package com.fastfood.client.model.dto

import jakarta.validation.constraints.NotBlank

data class ClientDTO(
    @field:NotBlank(message = "O nome não pode ser vazio")
    val name: String,

    @field:NotBlank(message = "O documento não pode ser vazio")
    val document: String
)

