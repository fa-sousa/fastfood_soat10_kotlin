package com.fastfood.order.domain

enum class OrderStatus(val description: String) {
    PENDING("Pendente"),
    PROCESSING("Em preparo"),
    READY("Pronto"),
    DELIVERED("Entregue"),
    CANCELLED("Cancelado")
}
