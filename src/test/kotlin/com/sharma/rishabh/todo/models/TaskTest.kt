package com.sharma.rishabh.todo.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class TaskTest {
    private lateinit var task: Task

    @BeforeEach
    fun beforeEach() {
        task = Task(
            id = 0,
            description = "Some Task",
            isDone = false,
            isDeleted = false
        )
    }

    @Test
    fun `Should mark the task as deleted`() {
        task.delete()
        assertEquals(task.isDeleted, true)
    }

    @Test
    fun `Should mark the task as done`() {
        task.markDone()
        assertEquals(task.isDone, true)
    }

    @Test
    fun `Should change the isDone status to false when markPending is called`() {
        task.markDone()
        assertEquals(task.isDone, true)
        task.markPending()
        assertEquals(task.isDone, false)
    }

    @Test
    fun `should update the description of the task`() {
        val expected = "New Description"
        task.updateDescription(expected)
        assertEquals(task.description, expected)
    }

    @Test
    fun isDone() {
        assertEquals(task.isDone, false)
    }

    @Test
    fun isDeleted() {
        assertEquals(task.isDeleted, false)
    }
}
