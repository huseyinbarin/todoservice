package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.dto.CreateTaskRequest
import com.challenge.todoservice.model.entity.Board
import com.challenge.todoservice.model.entity.Task
import java.util.UUID

interface BoardDataSource {
  fun getAllBoards(): List<Board>
  fun createBoard(board: Board): Board
  fun getBoardById(id: UUID): Board
  fun deleteBoard(id: UUID)
  fun createTaskInBoard(board: UUID, createTaskRequest: CreateTaskRequest): Task
  fun isBoardAvailable(id: UUID): Boolean
}
