package com.challenge.todoservice.controller

import com.challenge.todoservice.model.dto.CreateBoardRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest @Autowired constructor(
  val mockMvc: MockMvc,
  val objectMapper: ObjectMapper,
) {

  @Nested
  @DisplayName("GET /boards")
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  inner class GetBoards {
    @Test
    fun testGetBoards() {

      val newBoard = CreateBoardRequest("Board 1", "Description 1")

      // when
      mockMvc.post("/boards") {
        contentType = MediaType.APPLICATION_JSON
        content = objectMapper.writeValueAsString(newBoard)
      }


      mockMvc.perform(get("/boards"))
        .andDo(System.out::println)
        .andExpect(status().isOk)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(1))
        .andExpect(jsonPath("$[0].name").value("Board 1"))
        .andExpect(jsonPath("$[0].description").value("Description 1"))
    }
  }

  @Nested
  @DisplayName("POST /boards")
  @DirtiesContext
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  inner class CreateBoard {
    @Test
    fun testCreateBoard() {
      //Perform the POST request to create a board
      mockMvc.perform(
        post("/boards")
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            """
                {
                    "name": "New Board",
                    "description": "New Board Description"
                }
            """.trimIndent()
          )
      )
        .andExpect(status().isCreated)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("New Board"))
        .andExpect(jsonPath("$.description").value("New Board Description"))
    }
  }
}