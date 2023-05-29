package com.challenge.todoservice.model

data class Board(
  val id: String,
  val name: String,
  val description: String?,
  val tasks: List<Task>
)