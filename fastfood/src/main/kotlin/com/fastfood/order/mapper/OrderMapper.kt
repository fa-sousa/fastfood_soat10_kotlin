package com.fastfood.order.mapper

import com.fastfood.order.domain.Order
import com.fastfood.order.entity.OrderEntity
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface OrderMapper {
    fun toDomain(entity: OrderEntity): Order
    fun toEntity(model: Order): OrderEntity
}
