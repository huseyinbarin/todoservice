package com.challenge.todoservice.service

import com.challenge.todoservice.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(private val boardRepository: BoardRepository) {
}