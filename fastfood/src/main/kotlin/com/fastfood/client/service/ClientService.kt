package com.fastfood.client.service

import com.fastfood.client.model.ClientEntity
import com.fastfood.client.model.dto.ClientDTO
import com.fastfood.client.repository.ClientRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ClientService(private val clientRepository: ClientRepository) {

    fun getAllClients(): List<ClientDTO> {
        return clientRepository.findAll().map {
            ClientDTO(it.name, it.document)
        }
    }

    fun getClientById(id: Long): ClientDTO {
        val client = clientRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")
        }
        return ClientDTO(client.name, client.document)
    }

    fun createClient(dto: ClientDTO): ClientDTO {
        val entity = ClientEntity(name = dto.name, document = dto.document)
        val saved = clientRepository.save(entity)
        return ClientDTO(saved.name, saved.document)
    }

    fun updateClient(id: Long, dto: ClientDTO): ClientDTO {
        val existing = clientRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")
        }
        val updated = existing.copy(name = dto.name, document = dto.document)
        val saved = clientRepository.save(updated)
        return ClientDTO(saved.name, saved.document)
    }

    fun deleteClient(id: Long) {
        if (!clientRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found")
        }
        clientRepository.deleteById(id)
    }
}
