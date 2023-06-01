package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.entity.User
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.UUID

class UserDataSourceImplTest {

  @Test
  fun testDeleteUserSuccess() {
    // Arrange
    val userId = UUID.randomUUID()
    val userDataSource = UserDataSourceImpl()

    userDataSource.users[userId] = User(userId,"","","")

    // Act
    userDataSource.deleteUser(userId)

    // Assert
    assertTrue(userDataSource.users.isEmpty())
  }

  @Test
  fun testDeleteUserUserNotFound() {
    // Arrange
    val userId = UUID.randomUUID()
    val userDataSource = UserDataSourceImpl()
    @Suppress("UNCHECKED_CAST")
    val users = mock(MutableMap::class.java) as MutableMap<UUID, User>
    userDataSource.users.putAll(users)

    `when`(users.containsKey(userId)).thenReturn(false)

    // Act and Assert
    assertThrows(NoSuchElementException::class.java) {
      userDataSource.deleteUser(userId)
    }
  }
}
