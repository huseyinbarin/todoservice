package com.challenge.todoservice.service

import com.challenge.todoservice.datasource.TaskDataSource
import com.challenge.todoservice.model.dto.PartialUpdateTaskRequest
import com.challenge.todoservice.model.dto.UpdateTaskRequest
import com.challenge.todoservice.model.entity.Task
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TaskService(
  private val taskDataSource: TaskDataSource,
  private val userService: UserService,
  private val randomDataService: RandomDataService,
) {

  internal fun getTask(id: UUID): Task? {
    return taskDataSource.getTaskById(id)
  }

  internal fun deleteTask(id: UUID) {
    taskDataSource.deleteTask(id)
  }

  internal fun deleteAllTasksByUserId(userId: UUID) {
    taskDataSource.deleteAllTasksByUserId(userId)
  }

  internal fun updateTask(id: UUID, updatedTask: UpdateTaskRequest): Task {
    return taskDataSource.updateTask(id, updatedTask)
  }

  internal fun partialUpdateTask(id: UUID, partialUpdateTask: PartialUpdateTaskRequest): Task {
    return taskDataSource.partialUpdateTask(id, partialUpdateTask)
  }
}
