package br.com.messageproducer.domain.service

import br.com.messageproducer.domain.model.Card
import br.com.messageproducer.domain.repository.BoardRepository
import br.com.messageproducer.domain.repository.CardRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class CardService(val cardRepository: CardRepository, val boardRepository: BoardRepository) {

    fun create(cardRequest: Card): Card {
        if (cardRequest.board.id == null) throw BoardEmptyException()

        if (boardRepository.findById(cardRequest.board.id).isEmpty) throw BoardNotFoundException()

        return cardRepository.save(cardRequest)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class BoardNotFoundException : RuntimeException()

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class BoardEmptyException : RuntimeException()
}
