package com.challenge.todoservice.service

import com.challenge.todoservice.datasource.UserDataSource
import com.challenge.todoservice.model.entity.User
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(private val userDataSource: UserDataSource) {

  fun getUserById(userId: UUID): User {
    return userDataSource.getUserById(userId)
  }

  fun removeUser(userId: UUID) {
    userDataSource.deleteUser(userId)
  }

  fun addUser(userId: UUID){
    userDataSource.addUser(userId)
  }
}
