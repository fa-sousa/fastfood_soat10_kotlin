package com.fastfood.order.repository

import com.fastfood.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
