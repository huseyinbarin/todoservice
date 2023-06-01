package com.challenge.todoservice.service

import com.challenge.todoservice.datasource.BoardDataSource
import com.challenge.todoservice.datasource.TaskDataSource
import com.challenge.todoservice.model.dto.CreateTaskRequest
import com.challenge.todoservice.model.entity.Board
import com.challenge.todoservice.model.entity.Task
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardService(
  private val boardDataSource: BoardDataSource,
  private val taskDataSource: TaskDataSource,
  private val userService: UserService,
) {
  fun getAllBoards(): List<Board> {
    return boardDataSource.getAllBoards()
  }

  fun createBoard(board: Board): Board {

    // TODO For debugging purpose it will be removed
    println("board id: ${board.id}")
    return boardDataSource.createBoard(board)
  }

  fun getBoardById(boardId: UUID): Board {
    val board = boardDataSource.getBoardById(boardId)

    val enrichedTasks = board.tasks.map { task ->
      val user = userService.getUserById(task.user)
      task.copy(userDetail = user.toString())
    }

    return board.copy(tasks = enrichedTasks.toMutableList())
  }

  fun deleteBoard(boardId: UUID) {
    boardDataSource.deleteBoard(boardId);
  }

  fun createTaskInBoard(boardId: UUID, createTaskRequest: CreateTaskRequest): Task {

    if (boardDataSource.isBoardAvailable(boardId)) {
      userService.addUser(createTaskRequest.userId)
    }
    return boardDataSource.createTaskInBoard(boardId, createTaskRequest)
  }

  fun updateBoardsAfterUserDeletion(userId: UUID) {
    val boards = boardDataSource.getAllBoards()
    boards.forEach { board ->
      val tasksToRemove = board.tasks.filter { it.user == userId }
      tasksToRemove.forEach { board.tasks.remove(it) }
    }
  }
}