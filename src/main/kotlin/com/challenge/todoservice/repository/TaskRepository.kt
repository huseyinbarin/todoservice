package com.challenge.todoservice.repository

import com.challenge.todoservice.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TaskRepository : JpaRepository<Task,UUID> {

}