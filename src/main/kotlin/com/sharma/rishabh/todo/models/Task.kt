package com.sharma.rishabh.todo.models

data class Task(val id:Int, var description: String, var isDone:Boolean = false, var isDeleted: Boolean = false) {
    fun delete(): Boolean {
        this.isDeleted = true
        return true
    }

    fun markDone(): Boolean {
        this.isDone = true
        return true
    }

    fun markUndone(): Boolean {
        this.isDone = false
        return true
    }

    fun updateDescription(newDescription: String): String {
        this.description = newDescription
        return newDescription
    }
}
