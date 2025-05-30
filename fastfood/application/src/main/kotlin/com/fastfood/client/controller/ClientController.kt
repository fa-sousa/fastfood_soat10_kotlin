package com.fastfood.client.controller

import com.fastfood.client.model.dto.ClientDTO
import com.fastfood.client.service.ClientService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ClientController(private val clientService: ClientService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<ClientDTO>> =
        ResponseEntity.ok(clientService.getAllClients())

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ClientDTO> =
        ResponseEntity.ok(clientService.getClientById(id))

    @PostMapping
    fun create(@RequestBody @Valid clientDTO: ClientDTO): ResponseEntity<ClientDTO> =
        ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDTO))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid clientDTO: ClientDTO): ResponseEntity<ClientDTO> =
        ResponseEntity.ok(clientService.updateClient(id, clientDTO))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        clientService.deleteClient(id)
        return ResponseEntity.noContent().build()
    }
}
