package br.com.messageproducer.domain.service

import br.com.messageproducer.domain.model.Card
import br.com.messageproducer.domain.repository.CardRepository
import org.springframework.stereotype.Service

@Service
class CardService(val cardRepository: CardRepository) {

    fun create(cardRequest: Card): Card {
        return cardRepository.save(cardRequest)
    }
}
