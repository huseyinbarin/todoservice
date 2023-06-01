package com.challenge.todoservice.webhook

import com.challenge.todoservice.service.BoardService
import com.challenge.todoservice.service.TaskService
import com.challenge.todoservice.service.UserService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class WebhookService(
  private val taskService: TaskService,
  private val boardService: BoardService,
  private val userService: UserService
) {
  fun processUserDeletedWebhook(userId: UUID) {
    taskService.deleteAllTasksByUserId(userId)
    boardService.updateBoardsAfterUserDeletion(userId)
  }
}