package com.sharma.rishabh.todo.repositories.fs

import com.google.gson.Gson
import com.sharma.rishabh.todo.models.Task
import com.sharma.rishabh.todo.models.Todo
import com.sharma.rishabh.todo.repositories.Repository
import java.io.File

class FSRespository(val path: String): Repository {
    val gson = Gson()
    val file = File(path)
    override var todo: Todo? = null

    override fun loadTodo() {
        val todoJSON = file.readText(Charsets.UTF_8)
        this.todo = gson.fromJson(todoJSON, Todo::class.java)
    }

    override fun saveTodo() {
        file.writeText(gson.toJson(todo))
    }

    override fun listGroups(): MutableSet<String> {
        return this.todo!!.listGroups()
    }

    override fun addGroup(groupName: String): Boolean {
        return this.todo!!.addGroup(groupName)
    }

    override fun removeGroup(groupName: String): Boolean {
        return this.todo!!.removeGroup(groupName)
    }

    override fun addTask(groupName: String, taskDescription: String): Int {
        return this.todo!!.addTask(groupName,taskDescription)
    }

    override fun removeTask(groupName: String, taskId: Int): Int {
        return this.todo!!.removeTask(groupName, taskId)
    }

    override fun markDone(groupName: String, taskId: Int): Int {
        return this.todo!!.markDone(groupName, taskId)
    }

    override fun markPending(groupName: String, taskId: Int): Int {
        return this.todo!!.markPending(groupName, taskId)
    }

    override fun listTasks(groupName: String): List<Task> {
        return this.todo!!.listTasks(groupName)
    }
}
