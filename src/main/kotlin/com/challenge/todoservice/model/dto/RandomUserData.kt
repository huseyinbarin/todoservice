package com.challenge.todoservice.model.dto

data class RandomUserData(
  val login: RandomUserLogin,
  val location: RandomUserLocation,
  val email: String = "",
  val address: String = "",
)

data class RandomUserLocation(
  val city: String = "",
  val state: String = "",
  val country: String = "",
  val postcode: Int,
)

data class RandomUserLogin(
  val username: String = "",
)