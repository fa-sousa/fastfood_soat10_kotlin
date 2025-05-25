package com.fastfood.items.controller

import com.fastfood.items.repository.ItemRepository
import com.fastfood.order.entity.OrderItemEntity
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ItemController {
    @Autowired
    private val iRepository: ItemRepository? = null

    @get:GetMapping
    val all: ResponseEntity<MutableList<OrderItemEntity?>?>
        get() = ResponseEntity.ok<MutableList<OrderItemEntity?>?>(iRepository!!.findAll())

    @PutMapping
    fun put(@RequestBody oItems: @Valid OrderItemEntity): ResponseEntity<OrderItemEntity?> {
        if (iRepository!!.existsById(oItems.id)) {
            return ResponseEntity.status(HttpStatus.OK)
                .body<OrderItemEntity?>(iRepository.save<OrderItemEntity?>(oItems))
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build<OrderItemEntity?>()
    }
}
