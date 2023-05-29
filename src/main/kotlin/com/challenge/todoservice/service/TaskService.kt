package com.challenge.todoservice.service

import com.challenge.todoservice.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {
}