package com.challenge.todoservice.service

import com.challenge.todoservice.exception.ExternalServiceException
import com.challenge.todoservice.model.dto.RandomUserDataResponse
import com.challenge.todoservice.model.entity.User
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RandomDataService(
  private val restTemplate: RestTemplate,
) {

  private val RANDOM_USER_API = "https://randomuser.me/api/"

  private fun fetchRandomUserData(user: User): User {
    val response =
      restTemplate.getForObject(RANDOM_USER_API, RandomUserDataResponse::class.java)
    return response?.results?.first()?.let { result ->
      user.name = result.login.username
      user.email = result.email
      user.address = "${result.location.city}, ${result.location.state}, ${result.location.country}"
      user
    } ?: throw ExternalServiceException("Failed to fetch random user data")
  }
}