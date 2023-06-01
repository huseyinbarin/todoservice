package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.dto.CreateTaskRequest
import com.challenge.todoservice.model.entity.Board
import com.challenge.todoservice.model.entity.Task
import com.challenge.todoservice.model.entity.TaskStatus
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class BoardDataSourceImpl : BoardDataSource {
  private val boards: MutableMap<UUID, Board> = mutableMapOf()

  override fun getAllBoards(): List<Board> {
    return boards.values.toList()
  }

  override fun createBoard(board: Board): Board {
    boards[board.id] = board
    return board
  }

  override fun getBoardById(id: UUID): Board {
    if (boards.containsKey(id)) {
      return boards[id]!!
    }
    throw NoSuchElementException("Board not found with ID: $id")
  }
  override fun deleteBoard(id: UUID) {
    boards.remove(id)
  }
  override fun createTaskInBoard(boardId: UUID, createTaskRequest: CreateTaskRequest): Task {

    val newTask = Task(
      id = UUID.randomUUID(),
      name = createTaskRequest.name,
      description = createTaskRequest.description,
      status = TaskStatus.CREATED,
      user = createTaskRequest.userId
    )

    boards[boardId]?.tasks?.add(newTask)
    return newTask
  }

  override fun isBoardAvailable(id: UUID): Boolean {
    return boards.containsKey(id)
  }
}
