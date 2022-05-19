package br.com.messageproducer.domain.service

import br.com.messageproducer.config.ContextInitializer
import br.com.messageproducer.domain.model.Board
import br.com.messageproducer.domain.model.Card
import br.com.messageproducer.domain.repository.BoardRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
        board = Board(id = UUID.fromString("02cc5083-04ad-4c82-938b-2a9e2de66c28"), name = "Board")
        boardRepository.save(board)
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
}
