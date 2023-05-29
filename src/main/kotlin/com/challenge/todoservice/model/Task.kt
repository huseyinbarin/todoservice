package com.challenge.todoservice.model

data class Task(
  val id: String,
  val name: String,
  val description: String?,
  val user: String,
  val status: TaskStatus
)