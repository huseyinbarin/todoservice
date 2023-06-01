package com.challenge.todoservice.controller

import com.challenge.todoservice.common.BoardMapper
import com.challenge.todoservice.common.TaskMapper
import com.challenge.todoservice.model.BoardResource
import com.challenge.todoservice.model.TaskResource
import com.challenge.todoservice.model.dto.CreateBoardRequest
import com.challenge.todoservice.model.dto.CreateTaskRequest
import com.challenge.todoservice.model.entity.Board
import com.challenge.todoservice.service.BoardService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/boards")
class BoardController(private val boardService: BoardService) {

  @GetMapping
  fun getBoards(): List<BoardResource> {
    val boards = boardService.getAllBoards()
    return boards.map { BoardMapper.toResource(it) }
  }
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createBoard(@RequestBody boardRequest: CreateBoardRequest): BoardResource {
    val board = Board(UUID.randomUUID(), boardRequest.name, boardRequest.description, mutableListOf())
    return BoardMapper.toResource(boardService.createBoard(board))
  }
  @GetMapping("/{boardId}")
  fun getBoardById(@PathVariable boardId: UUID): Board {
    return boardService.getBoardById(boardId)
  }

  @DeleteMapping("/{boardId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteBoard(@PathVariable boardId: UUID) {
    boardService.deleteBoard(boardId)
  }

  @PostMapping("/{id}/tasks")
  @ResponseStatus(HttpStatus.CREATED)
  fun createTaskInBoard(@PathVariable id: UUID, @RequestBody taskRequest: CreateTaskRequest): TaskResource {
    val task = boardService.createTaskInBoard(id, taskRequest)
    return TaskMapper.toResource(task)
  }
}
