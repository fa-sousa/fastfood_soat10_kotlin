package com.fastfood.order.entity

import com.fastfood.client.model.ClientEntity
import com.fastfood.order.domain.OrderStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tb_orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val dateOrder: LocalDateTime = LocalDateTime.now(),

    var discountValue: Double = 0.0,

    @Column(name = "client_id")
    val clientId: Long,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderItems: MutableList<OrderItemEntity> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.PENDING,

    var totalValue: Double = 0.0
)
