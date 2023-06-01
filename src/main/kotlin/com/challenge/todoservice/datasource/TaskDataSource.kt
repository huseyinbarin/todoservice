package com.challenge.todoservice.datasource

import com.challenge.todoservice.model.dto.PartialUpdateTaskRequest
import com.challenge.todoservice.model.dto.UpdateTaskRequest
import com.challenge.todoservice.model.entity.Task
import java.util.UUID

interface TaskDataSource {

  fun getTaskById(id: UUID): Task
  fun updateTask(id: UUID,updateTaskRequest: UpdateTaskRequest): Task
  fun partialUpdateTask(id: UUID, taskUpdates: PartialUpdateTaskRequest): Task
  fun deleteTask(id: UUID)
  fun deleteAllTasksByUserId(userId: UUID)
}