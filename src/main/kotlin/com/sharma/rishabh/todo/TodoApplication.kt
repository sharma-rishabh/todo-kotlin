package com.sharma.rishabh.todo


import com.sharma.rishabh.todo.repositories.fs.FSRepository

//@SpringBootApplication
//class TodoApplication

fun main(args: Array<String>) {
    val command = mutableListOf("list-tasks", "newGroup")
    val re = FSRepository.intialize(System.getenv("HOME"))
    val handler = getHandler(command)
    handler(command, re)
}
