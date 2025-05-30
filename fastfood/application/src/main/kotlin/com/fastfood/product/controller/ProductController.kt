package com.fastfood.product.controller

import com.fastfood.product.model.Product
import com.fastfood.product.model.dto.ProductDTO
import com.fastfood.product.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductDTO>> {
        val products = productService.listAllProducts()
        val productDTOs = products.map { product ->
            ProductDTO(
                name = product.name,
                description = product.description,
                price = product.price,
                amount = product.amount,
                category = product.category
            )
        }
        return ResponseEntity.ok(productDTOs)
    }

    @PostMapping
    fun createProduct(@Valid @RequestBody product: Product): ResponseEntity<Product> {
        return try {
            val savedProduct = productService.savedProduct(product)
            ResponseEntity.status(HttpStatus.CREATED).body(savedProduct)
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }
}
