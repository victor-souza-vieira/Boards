package br.com.messageproducer.api.cards

import br.com.messageproducer.domain.model.Board
import br.com.messageproducer.domain.model.Card
import br.com.messageproducer.domain.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/cards")
class CardsController(val cardsService: CardService) {

    @PostMapping
    fun create(@RequestBody cardRequest: CardsRequest): ResponseEntity<CardsResponse> {
        val cardCreated = cardsService.create(cardRequest.toCard())

        return ResponseEntity.status(HttpStatus.CREATED).body(cardCreated.toResponse())
    }

    fun CardsRequest.toCard(): Card = Card(
        name = this.name,
        assignedTo = this.assignedTo,
        board = Board(this.boardId, "")
    )

    fun Card.toResponse(): CardsResponse = CardsResponse(
        id = this.id,
        name = this.name,
        assignedTo = this.assignedTo,
        boardId = this.board.id
    )
}
