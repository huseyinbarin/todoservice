package com.challenge.todoservice.model

import com.challenge.todoservice.model.entity.TaskStatus
import java.util.UUID

data class TaskResource(
  val id: UUID,
  val name: String = "",
  val description: String = "",
  val user: UUID,
  val status: TaskStatus
)