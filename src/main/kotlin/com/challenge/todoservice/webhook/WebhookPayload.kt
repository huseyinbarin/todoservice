package com.challenge.todoservice.webhook

import java.util.UUID

data class WebhookPayload(
  var data: WebhookData
)

data class WebhookData(
  val user: UUID
)
