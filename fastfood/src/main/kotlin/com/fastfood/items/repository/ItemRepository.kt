package com.fastfood.items.repository

import com.fastfood.items.model.OrderItems
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<OrderItems?, Long?>