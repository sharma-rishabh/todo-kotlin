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
        return todos.keys.filter { !todos[it]!!.isDeleted }.toMutableSet()
    }

    fun addTask(groupName: String, taskDescription: String): Int {
        val taskGroup = this.todos[groupName]
        if(taskGroup!!.isDeleted) {
            return -1
        }
        return taskGroup.createTask(taskDescription)
    }
    fun removeTask(groupName: String, taskId: Int): Int {
        val taskGroup = this.todos[groupName]
        if(taskGroup!!.isDeleted) {
            return -1
        }
        return taskGroup.removeTask(taskId)
    }
    fun markDone(groupName: String, taskId: Int): Int {
        val taskGroup = this.todos[groupName]
        if(taskGroup!!.isDeleted) {
            return -1
        }
        return taskGroup.markDone(taskId)
    }
    fun markPending(groupName: String, taskId: Int): Int {
        val taskGroup = this.todos[groupName]
        if(taskGroup!!.isDeleted) {
            return -1
        }
        return taskGroup.markPending(taskId)
    }
    fun listTasks(groupName: String): List<Task> {
        val taskGroup = this.todos[groupName]
        if(taskGroup!!.isDeleted) {
            return emptyList()
        }
        return taskGroup.listTasks()
    }
}
