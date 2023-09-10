package com.sharma.rishabh.todo.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class TaskGroupTest {
    private lateinit var  taskGroup: TaskGroup
    private val task  = Task(
        id = 1,
        description = "Some Task",
        isDone = false,
        isDeleted = false
    )
    @BeforeEach
    fun beforeEach() {
        val tasks: MutableList<Task> = mutableListOf()
        tasks.add(task)
        taskGroup = TaskGroup(
            groupName = "TaskGroup",
            tasks = tasks,
            isDeleted = false,
            id = 1
        )
    }
    @Test
    fun `should create a new task`() {
        val task = "New task"
        val taskId = taskGroup.createTask(task)
        assertEquals(taskId, 2)
        val addedTask = taskGroup.findTask(2)
        assertEquals(addedTask.description, task)
    }

    @Test
    fun `should remove a task`() {
        val idToRemove = 1
        val removedId = taskGroup.removeTask(idToRemove)
        assertEquals(removedId, idToRemove)

        val removedTask = taskGroup.findTask(idToRemove)
        assertEquals(removedTask.isDeleted, true)
    }

    @Test
    fun `should mar a task done`() {
        val idToMarkDone = 1
        val markedId = taskGroup.markDone(idToMarkDone)
        assertEquals(markedId, idToMarkDone)

        val markedTask = taskGroup.findTask(idToMarkDone)
        assertEquals(markedTask.isDone, true)
    }

    @Test
    fun `should mark a task pending`() {
        val idToMarkPending = 1
        val markedId = taskGroup.markPending(idToMarkPending)
        assertEquals(markedId, idToMarkPending)

        val markedTask = taskGroup.findTask(idToMarkPending)
        assertEquals(markedTask.isDone, false)
    }

    @Test
    fun `should update description of the given task id`() {
        val idToUpdate = 1
        val newDescription = "New Description"

        val markedId = taskGroup.updateDescription(idToUpdate, newDescription)
        assertEquals(markedId, idToUpdate)

        val markedTask = taskGroup.findTask(idToUpdate)
        assertEquals(markedTask.description, newDescription)
    }

    @Test
    fun `should list all not deleted tasks`() {
        val newTask = "New task"
        val taskId = taskGroup.createTask(newTask)
        taskGroup.removeTask(taskId)

        assertEquals(taskGroup.listTasks(), listOf(task))
    }

    @Test
    fun `should delete mark the task group as deleted`() {
        taskGroup.delete()
        assertEquals(taskGroup.isDeleted, true)
    }

}
