package com.challenge.todoservice.model.dto

import com.challenge.todoservice.model.entity.TaskStatus
import java.util.UUID

data class PartialUpdateTaskRequest(
  val name: String? = null,
  val description: String? = null,
  val userId: UUID? = null,
  val status: TaskStatus? = null
)