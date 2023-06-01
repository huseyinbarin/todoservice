package com.challenge.todoservice.model.dto

import java.util.UUID

data class CreateTaskRequest(
  val name: String = "",
  val description: String = "",
  val userId: UUID
)