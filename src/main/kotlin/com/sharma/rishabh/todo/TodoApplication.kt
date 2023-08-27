package com.sharma.rishabh.todo

import com.google.gson.Gson
import com.sharma.rishabh.todo.models.TaskGroup
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@SpringBootApplication
//class TodoApplication

fun main(args: Array<String>) {
//    runApplication<TodoApplication>(*args)
    val gson = Gson()
    val str = "{\"groupName\":\"new_group\",\"tasks\":[{\"id\":0,\"description\":\"Task task task\",\"isDone\":false,\"isDeleted\":false}],\"isDeleted\":false,\"id\":1}"

    val grp = gson.fromJson(str,TaskGroup::class.java)
    grp.markDone(0)
    println(gson.toJson(grp.tasks))
}
