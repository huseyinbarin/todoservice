package com.challenge.todoservice.model.entity

import java.util.UUID

data class User(
  val id: UUID,
  var name: String,
  var email: String = "",
  var address: String = ""
  // Other user properties
)