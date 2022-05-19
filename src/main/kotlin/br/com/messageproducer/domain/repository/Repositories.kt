package br.com.messageproducer.domain.repository

import br.com.messageproducer.domain.model.Board
import br.com.messageproducer.domain.model.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CardRepository : JpaRepository<Card, UUID>

@Repository
interface BoardRepository : JpaRepository<Board, UUID>
