package com.fastfood.order.service

import com.fastfood.client.repository.ClientRepository
import com.fastfood.order.model.Order
import com.fastfood.order.model.OrderItem
import com.fastfood.order.model.OrderStatus
import com.fastfood.order.repository.OrderRepository
import com.fastfood.product.service.ProductService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productService: ProductService,
    private val clientRepository: ClientRepository
) {
    @Transactional
    fun createOrder(clientId: Long, itemsRequest: List<OrderItemRequest>): Order {
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
            orderItems = items,
            discountValue = discount,
            totalValue = finalValue,
            status = OrderStatus.PENDING
        )

        return orderRepository.save(order) // Assuma que o repo converte Order -> OrderEntity
    }

    data class OrderItemRequest(val productId: Long, val quantity: Long)

    @Transactional
    fun finalizeOrder(orderId: Long): Order {
        val order = orderRepository.findById(orderId)
            .orElseThrow { RuntimeException("Pedido não encontrado com ID: $orderId") }

        if (order.status != OrderStatus.PENDING) {
            throw RuntimeException("Pedido não pode ser finalizado, status atual: ${order.status}")
        }

        order.orderItems.forEach { item ->
            val product = productService.buscarPorId(item.productId)

            if (product.amount < item.quantity) {
                throw RuntimeException("Estoque insuficiente para produto: ${product.name}")
            }

            productService.atualizarEstoque(product.id!!, product.amount - item.quantity)
        }

        val updatedOrder = order.copy(status = OrderStatus.PROCESSING)
        return orderRepository.save(updatedOrder)
    }

    fun getAllOrders(): List<Order> {
        return orderRepository.findAll()
    }
}