package com.fastfood.client.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "tb_clients")
data class ClientEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(message = "O nome não pode ser vazio")
    val name: String,

    @field:NotBlank(message = "O documento não pode ser vazio")
    val document: String
)
