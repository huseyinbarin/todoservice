package com.challenge.todoservice.common

import com.challenge.todoservice.model.BoardResource
import com.challenge.todoservice.model.TaskResource
import com.challenge.todoservice.model.entity.Board
import com.challenge.todoservice.model.entity.Task

object BoardMapper {
  fun toResource(board: Board): BoardResource {
    return BoardResource(
      name = board.name, description = board.description
    )
  }
}

object TaskMapper {

  fun toResource(task: Task): TaskResource {
    return TaskResource(
      id = task.id, name = task.name, description = task.description, user = task.user,
      status = task.status
    )
  }
}
