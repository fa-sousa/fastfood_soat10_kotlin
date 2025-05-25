package com.fastfood.order.controller

import com.fastfood.order.entity.OrderItemEntity
import com.fastfood.order.repository.ItemRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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