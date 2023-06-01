package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.dto.CreateTaskRequest
import com.challenge.todoservice.model.entity.Board
import com.challenge.todoservice.model.entity.Task
import com.challenge.todoservice.model.entity.TaskStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.UUID

class BoardDataSourceImplTest {

  @Mock
  private lateinit var boardDataSource: BoardDataSourceImpl

  @BeforeEach
  fun setUp() {
    MockitoAnnotations.openMocks(this)
  }

  @Test
  fun testGetAllBoards() {
    val board1 = Board(id = UUID.randomUUID(), "Board 1")
    val board2 = Board(id = UUID.randomUUID(), "Board 2")
    val expectedBoards = listOf(board1, board2)

    `when`(boardDataSource.getAllBoards()).thenReturn(expectedBoards)

    val actualBoards = boardDataSource.getAllBoards()

    assertEquals(expectedBoards, actualBoards)
  }

  @Test
  fun testCreateBoard() {
    val board = Board(UUID.randomUUID(), "New Board")

    `when`(boardDataSource.createBoard(board)).thenReturn(board)

    val createdBoard = boardDataSource.createBoard(board)

    assertNotNull(createdBoard)
    assertEquals(board, createdBoard)
  }

  @Test
  fun testGetBoardById_existingId() {
    val boardId = UUID.randomUUID()
    val board = Board(boardId, "Board")

    `when`(boardDataSource.getBoardById(boardId)).thenReturn(board)

    val retrievedBoard = boardDataSource.getBoardById(boardId)

    assertNotNull(retrievedBoard)
    assertEquals(board, retrievedBoard)
  }

  @Test
  fun testGetBoardById_nonExistingId() {
    val nonExistingId = UUID.randomUUID()

    `when`(boardDataSource.getBoardById(nonExistingId)).thenThrow(NoSuchElementException::class.java)

    assertThrows(NoSuchElementException::class.java) {
      boardDataSource.getBoardById(nonExistingId)
    }
  }

  @Test
  fun testDeleteBoard() {
    val boardId = UUID.randomUUID()

    boardDataSource.deleteBoard(boardId)

    assertFalse(boardDataSource.isBoardAvailable(boardId))
  }

  @Test
  fun testCreateTaskInBoard() {
    val boardId = UUID.randomUUID()
    val taskId = UUID.randomUUID()
    val userId = UUID.randomUUID()
    val createTaskRequest = CreateTaskRequest(name = "Task", description = "Description", userId = userId)
    val expectedTask = Task(id = taskId, name = "Task", description = "Description", status = TaskStatus.CREATED, user = userId)
    val board = Board(boardId, "Board")

    `when`(boardDataSource.createTaskInBoard(boardId, createTaskRequest)).then {
      val newTask = Task(
        id = taskId,
        name = createTaskRequest.name,
        description = createTaskRequest.description,
        status = TaskStatus.CREATED,
        user = createTaskRequest.userId
      )
      board.tasks.add(newTask)
      newTask
    }
    `when`(boardDataSource.isBoardAvailable(boardId)).thenReturn(true)

    val createdTask = boardDataSource.createTaskInBoard(boardId, createTaskRequest)

    assertNotNull(createdTask)
    assertEquals(expectedTask, createdTask)
    assertEquals(1, board.tasks.size)
    assertEquals(expectedTask, board.tasks[0])
  }
}
