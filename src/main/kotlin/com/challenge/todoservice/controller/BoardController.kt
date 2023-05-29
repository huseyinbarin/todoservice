package com.challenge.todoservice.controller

import com.challenge.todoservice.model.Board
import com.challenge.todoservice.service.BoardService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(private val boardService: BoardService) {

  @GetMapping("/boards")
  fun getAllBoards(): ResponseEntity<List<Board>> {
    // TODO: Implement the logic to get all boards from the service and return the response

    return ResponseEntity(HttpStatusCode.valueOf(200))
  }

  @PostMapping("/boards")
  fun createBoard(@RequestBody board: Board): ResponseEntity<Board> {
    // TODO: Implement the logic to create a board using the provided data and return the response

    return ResponseEntity(HttpStatusCode.valueOf(200))
  }

  // Implement other controller methods

}
