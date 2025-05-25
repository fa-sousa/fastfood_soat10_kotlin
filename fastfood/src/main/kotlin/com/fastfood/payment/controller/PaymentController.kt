package com.fastfood.payment.controller

import com.fastfood.payment.model.PaymentRequest
import com.fastfood.payment.service.PaymentService
import com.mercadopago.exceptions.MPException
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pagamentos")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class PaymentController(
    private val paymentService: PaymentService
) {

    @PostMapping
    fun createPayment(@RequestBody @Valid request: PaymentRequest): ResponseEntity<String> {
        return try {
            val url = paymentService.createPayment(request.value, request.description, request.email)
            ResponseEntity.ok(url)
        } catch (e: MPException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao criar pagamento: ${e.message}")
        }
    }
}
