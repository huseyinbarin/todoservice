package com.challenge.todoservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@RestControllerAdvice
class ExceptionHandler {
  @ExceptionHandler(value = [NotFoundException::class, IllegalArgumentException::class])
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  fun handleException(ex: Exception): ErrorResponse {
    return ErrorResponse(ex.message)
  }
}



