package com.sharma.rishabh.todo.repositories

import com.sharma.rishabh.todo.models.Task
import com.sharma.rishabh.todo.models.Todo

interface Repository {
    val todo: Todo?
    fun loadTodo()
    fun saveTodo()
    fun listGroups(): MutableSet<String>
    fun addGroup(groupName: String):Boolean
    fun removeGroup(groupName: String):Boolean
    fun addTask(groupName: String, taskDescription: String): Int
    fun removeTask(groupName: String, taskId: Int): Int
    fun markDone(groupName: String, taskId: Int): Int
    fun markPending(groupName: String, taskId: Int): Int
    fun listTasks(groupName: String): List<Task>
}
