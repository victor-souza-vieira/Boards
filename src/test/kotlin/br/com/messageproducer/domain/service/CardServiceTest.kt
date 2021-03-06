package br.com.messageproducer.domain.service

import br.com.messageproducer.config.ContextInitializer
import br.com.messageproducer.domain.model.Board
import br.com.messageproducer.domain.model.Card
import br.com.messageproducer.domain.repository.BoardRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@SpringBootTest
@Transactional
@ContextConfiguration(initializers = [ContextInitializer::class])
class CardServiceTest {

    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var boardRepository: BoardRepository

    lateinit var board: Board

    @BeforeEach
    fun `create test dependencies`() {
        board = boardRepository.save(Board(name = "Board"))
    }

    @Test
    fun `should create a new card`() {
        val expectedCard = Card(
            name = "New Card",
            assignedTo = UUID.randomUUID(),
            board = board
        )

        val createdCard = cardService.create(expectedCard)

        assertThat(createdCard.id).isNotNull
        assertThat(expectedCard.name).isEqualTo(createdCard.name)
        assertThat(expectedCard.assignedTo).isEqualTo(createdCard.assignedTo)
        assertThat(expectedCard.board.id).isEqualTo(createdCard.board.id)
    }

    @Test
    fun `given a non existent board id when try create a new card then a exception has been expected`() {
        val cardWithInvalidBoardId = Card(
            name = "New card",
            assignedTo = UUID.randomUUID(),
            board = Board(UUID.randomUUID(), "Invalid board")
        )

        assertThrows<CardService.BoardNotFoundException> { cardService.create(cardWithInvalidBoardId) }
    }
}
