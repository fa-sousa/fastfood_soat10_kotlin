package com.fastfood.order.service

import com.fastfood.client.repository.ClientRepository
import com.fastfood.order.domain.model.OrderItem
import com.fastfood.order.domain.Order
import com.fastfood.order.domain.OrderStatus
import com.fastfood.order.mapper.OrderMapper
import com.fastfood.order.repository.OrderRepository
import com.fastfood.product.service.ProductService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
open class OrderService(
    private val orderRepository: OrderRepository,
    private val productService: ProductService,
    private val clientRepository: ClientRepository,
    private val orderMapper: OrderMapper
) {
    data class OrderItemRequest(val productId: Long, val quantity: Long)

    @Transactional
    open fun createOrder(clientId: Long, itemsRequest: List<OrderItemRequest>): Order {
        val client = clientRepository.findById(clientId)
            .orElseThrow { RuntimeException("Cliente não encontrado com ID: $clientId") }

        val now = LocalDateTime.now()

        val items = itemsRequest.map {
            val product = productService.buscarPorId(it.productId)
            OrderItem(
                productId = it.productId,
                quantity = it.quantity,
                itemValue = product.price
            )
        }

        val total = items.sumOf { it.itemValue * it.quantity }
        val discount = if (total > 150.0) total * 0.05 else 0.0
        val finalValue = total - discount

        val order = Order(
            clientId = client.id!!,
            dateOrder = now,
            discountValue = discount,
            totalValue = finalValue,
            status = OrderStatus.PENDING,
            orderItems = items
        )

        val entity = orderMapper.toEntity(order)
        entity.orderItems.forEach { it.order = entity }

        return orderMapper.toDomain(orderRepository.save(entity))
    }

    @Transactional
    open fun finalizeOrder(orderId: Long): Order {
        val entity = orderRepository.findById(orderId)
            .orElseThrow { RuntimeException("Pedido não encontrado com ID: $orderId") }

        if (entity.status != OrderStatus.PENDING) {
            throw RuntimeException("Pedido não pode ser finalizado, status atual: ${entity.status}")
        }

        entity.orderItems.forEach { item ->
            val product = productService.buscarPorId(item.productId)

            if (product.amount < item.quantity) {
                throw RuntimeException("Estoque insuficiente para produto: ${product.name}")
            }

            productService.atualizarEstoque(product.id!!, product.amount - item.quantity)
        }

        entity.status = OrderStatus.PROCESSING
        return orderMapper.toDomain(orderRepository.save(entity))
    }

    open fun getAllOrders(): List<Order> {
        return orderRepository.findAll().map { orderMapper.toDomain(it) }
    }
}
