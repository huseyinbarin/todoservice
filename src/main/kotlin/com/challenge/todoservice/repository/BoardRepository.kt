package com.challenge.todoservice.repository

import com.challenge.todoservice.model.Board
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BoardRepository : JpaRepository<Board,UUID> {
}