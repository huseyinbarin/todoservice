package com.challenge.todoservice.model.dto

data class CreateBoardRequest(
  val name: String,
  val description: String = ""

)