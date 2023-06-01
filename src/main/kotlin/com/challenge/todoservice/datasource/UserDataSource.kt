package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.entity.User
import java.util.UUID

interface UserDataSource {
  fun getUserById(userId: UUID): User
  fun addUser(userId: UUID): User
  fun deleteUser(userId: UUID)
}