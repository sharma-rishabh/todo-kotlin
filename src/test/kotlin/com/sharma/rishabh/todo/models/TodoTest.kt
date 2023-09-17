package com.sharma.rishabh.todo.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class TodoTest {
    private lateinit var  taskGroup: TaskGroup
    private lateinit var task: Task
    private lateinit var todo: Todo

    @BeforeEach
    fun beforeEach() {
        task  = Task(
            id = 1,
            description = "Some Task",
            isDone = false,
            isDeleted = false
        )
        val tasks: MutableList<Task> = mutableListOf()
        tasks.add(task)
        taskGroup = TaskGroup(
            groupName = "TaskGroup",
            tasks = tasks,
            isDeleted = false,
            id = 1
        )

        todo = Todo(mutableMapOf("TaskGroup" to taskGroup))
    }
    @Test
    fun `should add group`() {
        val groupName = "newGroup"
        val actual  = todo.addGroup(groupName)
        assertTrue(actual)

       assertTrue(todo.listGroups().contains(groupName))
    }

    @Test
    fun removeGroup() {
        val actual = todo.removeGroup("TaskGroup")
        assertTrue(actual)

        assertTrue(todo.listGroups().isEmpty())
    }

    @Test
    fun listGroups() {
        val expected = mutableSetOf("TaskGroup")
        assertEquals(todo.listGroups(), expected)
    }

    @Test
    fun `should add Task`() {
        val actual = todo.addTask("TaskGroup", "New Task")
        assertEquals(2, actual)
    }

    @Test
    fun `should not add task to removed task group`() {
        todo.removeGroup("TaskGroup")
        val actual = todo.addTask("TaskGroup", "brand new task")
        assertEquals(-1, actual)
    }

    @Test
    fun `should remove Task`() {
        val actual = todo.removeTask("TaskGroup", 1)
        assertEquals(1, actual)
    }

    @Test
    fun `should not remove task to removed task group`() {
        todo.removeGroup("TaskGroup")
        val actual = todo.removeTask("TaskGroup", 1)
        assertEquals(-1, actual)
    }

    @Test
    fun `should mark task done`() {
        val actual = todo.markDone("TaskGroup", 1)
        assertEquals(1, actual)
    }

    @Test
    fun `should not mark task done of removed task group`() {
        todo.removeGroup("TaskGroup")
        val actual = todo.markDone("TaskGroup", 1)
        assertEquals(-1, actual)
    }

    @Test
    fun `should mark task pending`() {
        val actual = todo.markPending("TaskGroup", 1)
        assertEquals(1, actual)
    }

    @Test
    fun `should not mark task pending of removed task group`() {
        todo.removeGroup("TaskGroup")
        val actual = todo.markPending("TaskGroup", 1)
        assertEquals(-1, actual)
    }

    @Test
    fun `should list t1asks`() {
        val actual = todo.listTasks("TaskGroup")
        assertEquals(taskGroup.listTasks(), actual)
    }
}
