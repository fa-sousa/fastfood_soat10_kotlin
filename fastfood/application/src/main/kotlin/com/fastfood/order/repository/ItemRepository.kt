package com.fastfood.order.repository

import com.fastfood.order.entity.OrderItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<OrderItemEntity?, Long?>