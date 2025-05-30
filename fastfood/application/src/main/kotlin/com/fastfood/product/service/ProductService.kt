package com.fastfood.product.service

import com.fastfood.product.model.Product
import com.fastfood.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ProductService(
    private val repository: ProductRepository
) {
    fun listAllProducts(): List<Product> = repository.findAll()

    @Transactional
    open fun savedProduct(product: Product): Product = repository.save(product)

    @Transactional
    open fun delete(id: Long) = repository.deleteById(id)

    @Transactional
    open fun updateStock(id: Long, newStock: Long) {
        val product = findById(id)
        product.amount = newStock
        repository.save(product)
    }

    @Transactional
    open fun reduceStock(id: Long, quantity: Long) {
        val product = findById(id)
        if (product.amount < quantity) {
            throw IllegalStateException("Estoque insuficiente para o produto: ${product.name}")
        }
        product.amount -= quantity
        repository.save(product)
    }

    open fun findById(id: Long): Product {
        return repository.findById(id)
            .orElseThrow { IllegalArgumentException("Produto com ID $id não encontrado") }
    }
}
