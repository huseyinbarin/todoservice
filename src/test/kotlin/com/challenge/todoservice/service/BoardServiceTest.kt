package com.challenge.todoservice.serviceimport

import com.challenge.todoservice.datasource.BoardDataSource
import com.challenge.todoservice.datasource.TaskDataSource
import com.challenge.todoservice.model.entity.Board
import com.challenge.todoservice.model.entity.Task
import com.challenge.todoservice.model.entity.TaskStatus
import com.challenge.todoservice.model.entity.User
import com.challenge.todoservice.service.BoardService
import com.challenge.todoservice.service.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.UUID

class BoardServiceTest {
  private lateinit var boardDataSource: BoardDataSource
  private lateinit var taskDataSource: TaskDataSource
  private lateinit var userService: UserService
  private lateinit var boardService: BoardService

  @BeforeEach
  fun setup() {
    boardDataSource = mock(BoardDataSource::class.java)
    taskDataSource = mock(TaskDataSource::class.java)
    userService = mock(UserService::class.java)
    boardService = BoardService(boardDataSource, taskDataSource, userService)
  }

  @Test
  fun testGetAllBoards() {
    val board1 = Board(UUID.randomUUID(), "Board 1")
    val board2 = Board(UUID.randomUUID(), "Board 2")
    val boards = listOf(board1, board2)

    `when`(boardDataSource.getAllBoards()).thenReturn(boards)

    val result = boardService.getAllBoards()

    assertEquals(boards, result)
  }

  @Test
  fun testCreateBoard() {
    val boardId = UUID.randomUUID()
    val board = Board(boardId, "Board")

    `when`(boardDataSource.createBoard(board)).thenReturn(board)

    val result = boardService.createBoard(board)

    assertEquals(board, result)
  }

  @Test
  fun testGetBoardById() {
    val boardId = UUID.randomUUID()
    val taskId = UUID.randomUUID()
    val userId = UUID.randomUUID()
    val board = Board(boardId, "Board")
    val task = Task(taskId, "Task", "Description",userId, TaskStatus.CREATED)
    board.tasks.add(task)

    `when`(boardDataSource.getBoardById(boardId)).thenReturn(board)
    `when`(userService.getUserById(userId)).thenReturn(User(id = userId,"huseyin","abs","sdfsfs"))

    val result = boardService.getBoardById(boardId)

    assertNotNull(result)
    assertEquals(boardId, result.id)
    assertEquals(1, result.tasks.size)
    assertEquals(userId, result.tasks[0].user)
  }

  @Test
  fun testDeleteBoard() {
    val boardId = UUID.randomUUID()

    boardService.deleteBoard(boardId)

    // Verify that the deleteBoard method of the boardDataSource was called with the correct boardId
    Mockito.verify(boardDataSource).deleteBoard(boardId)
  }

}