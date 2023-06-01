package com.challenge.todoservice.controller

import com.challenge.todoservice.common.TaskMapper
import com.challenge.todoservice.model.TaskResource
import com.challenge.todoservice.model.dto.PartialUpdateTaskRequest
import com.challenge.todoservice.model.dto.UpdateTaskRequest
import com.challenge.todoservice.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

  @PutMapping("/{id}")
  fun updateTask(@PathVariable id: UUID, @RequestBody updateTaskRequest: UpdateTaskRequest): TaskResource {
    val updatedTask = taskService.updateTask(id, updateTaskRequest)
    return TaskMapper.toResource(updatedTask)
  }

  @PatchMapping("/{id}")
  fun partialUpdateTask(@PathVariable id: UUID, @RequestBody taskRequest: PartialUpdateTaskRequest): TaskResource {
    val updatedTask = taskService.partialUpdateTask(id, taskRequest)
    return TaskMapper.toResource(updatedTask)
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteTask(@PathVariable id: UUID) {
    taskService.deleteTask(id)
  }
}
