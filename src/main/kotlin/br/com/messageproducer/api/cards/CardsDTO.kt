package br.com.messageproducer.api.cards

import java.util.UUID

data class CardsRequest(
    val name: String,
    val assignedTo: UUID,
    val boardId: UUID
)

data class CardsResponse(
    val id: UUID? = null,
    val name: String,
    val assignedTo: UUID,
    val boardId: UUID?
)
