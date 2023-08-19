package com.sharma.rishabh.todo.models

data class Todo(val todos: MutableMap<String, TaskGroup>) {
    fun addGroup(groupName: String): Boolean {
        todos[groupName] = TaskGroup(groupName, mutableListOf())
        return true
    }

    fun removeGroup(groupName: String): Boolean {
        val taskGroup = todos[groupName]
        taskGroup?.delete()
        return true
    }

    fun listGroups(): MutableSet<String> {
        return todos.keys
    }
}
