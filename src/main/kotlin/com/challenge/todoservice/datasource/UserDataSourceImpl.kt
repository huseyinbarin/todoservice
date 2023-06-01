package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.entity.User
import com.challenge.todoservice.webhook.WebhookData
import com.challenge.todoservice.webhook.WebhookPayload
import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.UUID

private const val MAX_RETRIES = 3
private const val RETRY_DELAY_MS = 1000
private const val WEBHOOK_URL = "http://localhost:8080/webhooks/user-deleted"

@Repository
class UserDataSourceImpl : UserDataSource {
  val users: MutableMap<UUID, User> = mutableMapOf()
  val gson = Gson()
  override fun getUserById(userId: UUID): User {

    if (users.containsKey(userId)) {
      return users[userId]!!
    }

    throw NoSuchElementException("User not found with ID: $userId")
  }

  override fun addUser(userId: UUID): User {
    val user = User(userId, "", "", "")
    users[userId] = user
    return user
  }

  override fun deleteUser(userId: UUID) {
    // TODO: have a concern here in terms of consistency, using a MessageBroker could be a better idea)
    if (users.containsKey(userId)) {
      users.remove(userId)
      // trigger the user-deleted webhook
      val payload = WebhookPayload(
        data = WebhookData(user = userId)
      )
      sendUserDeletedWebhook(payload)
    } else {
      throw NoSuchElementException("User not found with ID: $userId")
    }
  }

  private fun sendUserDeletedWebhook(payload: WebhookPayload) {
    var retries = 0
    var success = false

    while (retries < MAX_RETRIES && !success) {
      try {

        val request = HttpRequest.newBuilder()
          .uri(URI.create(WEBHOOK_URL))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
          .build()

        val client = HttpClient.newBuilder().build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() == HttpStatus.OK.value()) {
          success = true
        } else {
          retries++
          Thread.sleep(RETRY_DELAY_MS * 1L)
        }
      } catch (e: IOException) {
        retries++
        Thread.sleep(RETRY_DELAY_MS * 1L)
      }
    }

    if (!success) {
      // TODO:  logging an error, or taking alternative actions for the future
      println("Notification request failed Do not push the limit.")
    }
  }
}
