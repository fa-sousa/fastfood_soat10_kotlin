package com.fastfood.order.model

import com.fastfood.client.model.ClientEntity
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    val client: ClientEntity,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderItems: MutableList<OrderItemEntity> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.PENDING,

    var totalValue: Double = 0.0
)