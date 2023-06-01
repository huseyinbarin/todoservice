package com.challenge.todoservice.webhook

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/webhooks")
class WebhookController(private val webhookService: WebhookService) {
  @PostMapping("/user-deleted")
  fun handleUserDeletedWebhook(@RequestBody payload: WebhookPayload) {
    val userId = payload.data.user
    webhookService.processUserDeletedWebhook(userId)
  }
}