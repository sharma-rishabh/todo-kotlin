package com.sharma.rishabh.todo


import com.sharma.rishabh.todo.repositories.fs.FSRepository
import com.sharma.rishabh.todo.views.View
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoApplication


val localPrinter: (String) -> Unit = {
    println(it)
}
fun main(args: Array<String>) {
    runApplication<TodoApplication>(*args)

//    val command = mutableListOf("add-task", "NewGroup", "NEW TASK")
//    val re = FSRepository(System.getenv("HOME"))
//    val view = View(localPrinter)
//    val handler = getHandler(command)
//    handler(command, re, view)
}
