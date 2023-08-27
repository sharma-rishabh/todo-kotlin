package com.sharma.rishabh.todo.models

data class TaskGroup(
    val groupName: String,
    val tasks: MutableList<Task>,
    var isDeleted: Boolean = false,
    var id: Int = 0
) {

    fun createTask(description: String): Int {
        val task = Task(id, description)
        id++
        tasks.add(task)

        return id
    }

    fun findTask(id: Int): Task {
        return this.tasks.first {
            it.id == id
        }
    }

    fun removeTask(id: Int): Int {
        val task = this.findTask(id)
        task.delete()
        return id
    }

    fun markDone(id: Int): Int {
        val task = this.findTask(id)
        task.markDone()
        return id
    }

    fun markPending(id: Int): Int {
        val task = this.findTask(id)
        task.markPending()
        return id
    }

    fun updateDescription(id: Int, newDescription: String): Int {
        val task = this.findTask(id)
        task.markPending()
        return id
    }

    fun listTasks(): List<Task> {
        return this.tasks.filter {
            !it.isDeleted
        }
    }

    fun delete(): Boolean {
        this.isDeleted = true
        return isDeleted
    }
}
