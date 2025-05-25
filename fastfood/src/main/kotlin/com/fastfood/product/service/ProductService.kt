package com.fastfood.product.service

import com.fastfood.product.model.Product
import com.fastfood.product.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ProductService(
    private val repository: ProductRepository
) {
    fun listarTodos(): List<Product> = repository.findAll()

    @Transactional
    open fun salvar(produto: Product): Product = repository.save(produto)

    @Transactional
    open fun deletar(id: Long) = repository.deleteById(id)

    @Transactional
    open fun atualizarEstoque(id: Long, novoEstoque: Long) {
        val produto = buscarPorId(id)
        produto.amount = novoEstoque
        repository.save(produto)
    }

    @Transactional
    open fun diminuirEstoque(id: Long, quantidade: Long) {
        val produto = buscarPorId(id)
        if (produto.amount < quantidade) {
            throw IllegalStateException("Estoque insuficiente para o produto: ${produto.name}")
        }
        produto.amount -= quantidade
        repository.save(produto)
    }

    open fun buscarPorId(id: Long): Product {
        return repository.findById(id)
            .orElseThrow { IllegalArgumentException("Produto com ID $id não encontrado") }
    }
}
