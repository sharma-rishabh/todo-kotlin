package com.sharma.rishabh.todo


import com.sharma.rishabh.todo.repositories.fs.FSRepository
import com.sharma.rishabh.todo.views.View

//@SpringBootApplication
//class TodoApplication


val localPrinter: (String) -> Unit = {
    println(it)
}
fun main(args: Array<String>) {
    val command = mutableListOf("list-groups")
    val re = FSRepository.intialize(System.getenv("HOME"))
    val view = View(localPrinter)
    val handler = getHandler(command)
    handler(command, re, view)
}
