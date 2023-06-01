package com.challenge.todoservice.model.entity

import java.util.UUID

// Board entity
data class Board(
  val id: UUID,
  val name: String = "",
  val description: String = "",
  val tasks: MutableList<Task> = mutableListOf()
)

