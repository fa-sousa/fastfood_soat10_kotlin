package com.fastfood.items.model


import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator
import com.fastfood.order.model.Order
import com.fastfood.product.model.Product
import jakarta.persistence.*

@Entity
@Table(name = "tb_items")
@JsonIdentityInfo(generator = PropertyGenerator::class, property = "id")
class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    private var order: Order? = null // Removido @JsonBackReference

    @ManyToOne
    var product: Product? = null // Removido @JsonBackReference

    var quantity: Long? = null

    var itemValeu: Double? = null

    fun getOrder(): Order? {
        return order
    }

    fun setOrder(order: Order?) {
        this.order = order
    }
}