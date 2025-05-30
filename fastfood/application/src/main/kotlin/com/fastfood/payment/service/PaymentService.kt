package com.fastfood.payment.service

import com.mercadopago.exceptions.MPException
import com.mercadopago.resources.Preference
import com.mercadopago.resources.datastructures.preference.Item
import com.mercadopago.resources.datastructures.preference.Payer
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentService {

    @Throws(MPException::class)
    fun createPayment(value: BigDecimal, description: String, email: String): String {
        val preference = Preference()

        val item = Item()
            .setTitle(description)
            .setQuantity(1)
            .setUnitPrice(value.toFloat())

        val payer = Payer()
            .setEmail(email)

        preference.appendItem(item)
        preference.payer = payer
        preference.save()

        return preference.initPoint
    }
}
