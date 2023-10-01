package com.sharma.rishabh.todo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.io.File
import java.nio.file.Paths

@SpringBootTest
@AutoConfigureMockMvc
class APIControllerTest (
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper,
    @Value("\${todo.home}") private val home: String
) {

    val baseUrl = "/api/todo"

    @BeforeEach
    fun beforeEach() {
        val homePath = Paths.get(home)
        val todoDir = ".todo"
        val dataFile = "data.json"
        val dirPath = homePath.resolve(Paths.get(todoDir))
        val dataPath = dirPath.resolve(Paths.get(dataFile))
        val file = File(dataPath.toString())
        val content = """{
  "todos": {
    "NewGroup": {
      "groupName": "NewGroup",
      "tasks": [
        {
          "id": 1,
          "description": "NEW TASK",
          "isDone": false,
          "isDeleted": false
        },
        {
          "id": 2,
          "description": "OLD TASK",
          "isDone": false,
          "isDeleted": false
        }
      ],
      "isDeleted": false,
      "id": 2
    },
    "DeletedGroup": {
      "groupName": "Group",
      "tasks": [
        {
          "id": 1,
          "description": "NEW TASK",
          "isDone": false,
          "isDeleted": false
        },
        {
          "id": 2,
          "description": "OLD TASK",
          "isDone": false,
          "isDeleted": false
        }
      ],
      "isDeleted": true,
      "id": 2
    },
    "Group": {
      "groupName": "Group",
      "tasks": [
        {
          "id": 1,
          "description": "NEW TASK",
          "isDone": false,
          "isDeleted": true
        },
        {
          "id": 2,
          "description": "OLD TASK",
          "isDone": false,
          "isDeleted": false
        },
        {
          "id": 3,
          "description": "DELETED TASK",
          "isDone": false,
          "isDeleted": true
        }
      ],
      "isDeleted": false,
      "id": 2
    }
  }
}
"""
        file.writeText(content)
    }

    @Test
    fun `should list groups`() {
        mockMvc
            .get("$baseUrl/list-groups")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0]") { value("NewGroup") }
                jsonPath("$[1]") { value("Group") }
            }
    }

    @Test
    fun `should list tasks`() {
        mockMvc
            .get("$baseUrl/list-tasks/Group")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].description") { value("OLD TASK") }
                jsonPath("$[0].done") { value(false) }
                jsonPath("$[0].deleted") { value(false) }
            }
    }

    @Test
    fun `should mark a task done`() {
        val addTaskRequest = AddTask("Group", "CREATED TASK")

        mockMvc
            .post("$baseUrl/add-task") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(addTaskRequest)
            }
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.taskId"){value(3)}
            }
    }
}
