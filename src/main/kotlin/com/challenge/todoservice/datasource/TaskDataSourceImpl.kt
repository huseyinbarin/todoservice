package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.dto.PartialUpdateTaskRequest
import com.challenge.todoservice.model.dto.UpdateTaskRequest
import com.challenge.todoservice.model.entity.Task
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class TaskDataSourceImpl : TaskDataSource {
  private val tasks: MutableMap<UUID, Task> = mutableMapOf()

  override fun getTaskById(id: UUID): Task {
    if (tasks.containsKey(id)) {
      return tasks[id]!!
    }
    throw NoSuchElementException("Task not found with ID: $id")
  }

  override fun updateTask(id: UUID, updateTaskRequest: UpdateTaskRequest): Task {
    if (tasks.containsKey(id)) {
      tasks[id]!!.name = updateTaskRequest.name
      tasks[id]!!.description = updateTaskRequest.description
      tasks[id]!!.status = updateTaskRequest.status
      tasks[id]!!.user = updateTaskRequest.user
      return tasks[id]!!
    }
    throw NoSuchElementException("Task not found with ID: $id")
  }

  override fun partialUpdateTask(id: UUID, taskUpdates: PartialUpdateTaskRequest): Task {
    val existingTask = tasks[id]
    if (existingTask != null) {
      val updatedTask = Task(
        id = existingTask.id,
        name = taskUpdates.name ?: existingTask.name,
        description = taskUpdates.description ?: existingTask.description,
        user = existingTask.user,
        status = existingTask.status
      )

      tasks[id] = updatedTask
      return updatedTask
    }
    throw NoSuchElementException("Task not found with ID: $id")
  }

  override fun deleteTask(id: UUID) {
    tasks.remove(id)
  }

  override fun deleteAllTasksByUserId(userId: UUID) {
    tasks.values.removeIf { it.user == userId }
  }
}
