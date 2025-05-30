package com.fastfood.payment.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PaymentRequest(
    @field:NotNull(message = "O valor do pagamento é obrigatório")
    val value: BigDecimal,

    @field:NotBlank(message = "A descrição é obrigatória")
    val description: String,

    @field:NotBlank(message = "O e-mail é obrigatório")
    @field:Email(message = "E-mail inválido")
    val email: String
)
