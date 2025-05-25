package com.fastfood.items.controller

import com.fastfood.items.model.OrderItems
import com.fastfood.items.repository.ItemRepository
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
    val all: ResponseEntity<MutableList<OrderItems?>?>
        get() = ResponseEntity.ok<MutableList<OrderItems?>?>(iRepository!!.findAll())

    @PutMapping
    fun put(@RequestBody oItems: @Valid OrderItems): ResponseEntity<OrderItems?> {
        if (iRepository!!.existsById(oItems.id)) {
            return ResponseEntity.status(HttpStatus.OK)
                .body<OrderItems?>(iRepository.save<OrderItems?>(oItems))
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build<OrderItems?>()
    }
}
