package com.sharma.rishabh.todo

data class AddTask(
    val groupName: String,
    val taskDescription: String
)

data class MutateTask(
    val groupName: String,
    val taskId: Int
)

data class AddGroup(
    val groupName: String,
)
