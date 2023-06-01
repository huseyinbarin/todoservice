package com.challenge.todoservice.model.entity

import java.util.UUID

data class Task(
  var id: UUID,
  var name: String = "",
  var description: String = "",
  var user: UUID,
  var status: TaskStatus
)

