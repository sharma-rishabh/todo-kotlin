package com.sharma.rishabh.todo

import com.sharma.rishabh.todo.repositories.Repository

val  handleAddTask: (List<String>, Repository) -> Unit = { args, repository ->
    val groupName: String = args[1]
    val taskName: String = args[2]
    val response = repository.addTask(groupName, taskName)
    repository.saveTodo()
    println("Task added to $groupName with id $response .")
}
val  handleRemoveTask: (List<String>, Repository) -> Unit = { args, repository ->
    val groupName = args[1]
    val taskId = args[2].toInt()
    val response = repository.removeTask(groupName, taskId)
    repository.saveTodo()
    println("Task removed from $groupName with id $response .")
}

val  handleAddGroup: (List<String>, Repository) -> Unit = { args, repository ->
    val groupName: String = args[1]
    val response = repository.addGroup(groupName)
    repository.saveTodo()
    println("Group $response added.")
}
val  handleRemoveGroup: (List<String>, Repository) -> Unit = { args, repository ->
    val groupName: String = args[1]
    val response = repository.removeGroup(groupName)
    repository.saveTodo()
    println("Group $response removed.")
}

val  handleMarkDone: (List<String>, Repository) -> Unit = { args, repository ->
    val groupName: String = args[1]
    val taskId = args[2].toInt()
    val response = repository.markDone(groupName, taskId)
    repository.saveTodo()
    println("Task $response in group $groupName marked done.")
}


val  handleMarkPending: (List<String>, Repository) -> Unit = { args, repository ->
    val groupName: String = args[1]
    val taskId = args[2].toInt()
    val response = repository.markPending(groupName, taskId)
    repository.saveTodo()
    println("Task $response in group $groupName marked done.")
}

val  handleListTasks: (List<String>, Repository) -> Unit = { args, repository ->
    val groupName: String = args[1]
    val response = repository.listTasks(groupName)
    println(response)
}

val  handleListGroups: (List<String>, Repository) -> Unit = { args, repository ->
    val response = repository.listGroups()
    println(response)
}


val  handleNoMatch: (List<String>, Repository) -> Unit = { args, repository ->
    println("Command not found")
}

fun getHandler(args: List<String>) : (List<String>, Repository)->Unit {
    return when(args[0]) {
        "add-task" -> handleAddTask
        "add-group" -> handleAddGroup
        "remove-task" -> handleRemoveTask
        "remove-group" -> handleRemoveGroup
        "mark-done" -> handleMarkDone
        "mark-pending" -> handleMarkPending
        "list-tasks" -> handleListTasks
        "list-groups" -> handleListGroups
        else -> handleNoMatch

    }
}
