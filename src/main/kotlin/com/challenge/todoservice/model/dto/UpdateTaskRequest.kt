package com.challenge.todoservice.model.dto

import com.challenge.todoservice.model.entity.TaskStatus
import java.util.UUID

data class UpdateTaskRequest(
  val name: String,
  val description: String,
  val user: UUID,
  val status: TaskStatus
)