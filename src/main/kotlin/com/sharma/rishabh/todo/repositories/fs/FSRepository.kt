package com.sharma.rishabh.todo.repositories.fs

import com.google.gson.Gson
import com.sharma.rishabh.todo.models.Task
import com.sharma.rishabh.todo.models.Todo
import com.sharma.rishabh.todo.repositories.Repository
import org.springframework.beans.factory.annotation.Value
import java.io.File
import java.nio.file.Paths
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

@org.springframework.stereotype.Repository
class FSRepository(
    @Value("\${todo.home}")
    private val home: String
): Repository {
    private lateinit var path: String
    private val gson = Gson()
    private lateinit var file: File
    override var todo: Todo? = null

    init {
        val homePath = Paths.get(home)
        val todoDir = ".todo"
        val dataFile = "data.json"
        val dirPath = homePath.resolve(Paths.get(todoDir))
        if (!dirPath.exists()) {
            dirPath.createDirectory()
        }
        val dataPath = dirPath.resolve(Paths.get(dataFile))
        if(!dataPath.exists()) {
            val file = File(dataPath.toString())
            file.writeText("{\"todos\":{}}")
        }
        this.path = dataPath.toString()
        this.file = File(this.path)
        this.loadTodo()
    }

    override fun loadTodo(): Boolean {
        val todoJSON = file.readText(Charsets.UTF_8)
        this.todo = gson.fromJson(todoJSON, Todo::class.java)
        return true
    }

    override fun saveTodo(): Boolean {
        file.writeText(gson.toJson(todo))
        return true
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
