package com.sharma.rishabh.todo

import com.sharma.rishabh.todo.repositories.Repository
import com.sharma.rishabh.todo.views.View

val  handleAddTask: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val groupName: String = args[1]
    val taskName: String = args[2]
    val response = repository.addTask(groupName, taskName)
    repository.saveTodo()
    view.showAddTaskResponse(response, groupName)
}

val  handleRemoveTask: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val groupName = args[1]
    val taskId = args[2].toInt()
    val response = repository.removeTask(groupName, taskId)
    repository.saveTodo()
    view.showRemoveTaskResponse(taskId, response, groupName)
}

val  handleAddGroup: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val groupName: String = args[1]
    val response = repository.addGroup(groupName)
    repository.saveTodo()
    view.showAddGroupResponse(response, groupName)
}

val  handleRemoveGroup: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val groupName: String = args[1]
    val response = repository.removeGroup(groupName)
    repository.saveTodo()
    view.showRemoveGroupResponse(response, groupName)
}

val  handleMarkDone: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val groupName: String = args[1]
    val taskId = args[2].toInt()
    val response = repository.markDone(groupName, taskId)
    repository.saveTodo()
    view.showMarkDoneResponse(taskId, response, groupName)
}

val  handleMarkPending: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val groupName: String = args[1]
    val taskId = args[2].toInt()
    val response = repository.markPending(groupName, taskId)
    repository.saveTodo()
    view.showMarkPendingResponse(taskId, response, groupName)
}

val  handleListTasks: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val groupName: String = args[1]
    val response = repository.listTasks(groupName)
    view.showListTasksResponse(response, groupName)
}

val  handleListGroups: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    val response = repository.listGroups()
    view.showListGroupResponse(response)
}


val  handleHelp: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    view.showHelpResponse()
}

val  handleNoMatch: (List<String>, Repository, View) -> Unit = { args, repository, view ->
    view.showNoMatchResponse(args[0])
}

fun getHandler(args: List<String>) : (List<String>, Repository, View)->Unit {
    return when(args[0]) {
        "add-task" -> handleAddTask
        "add-group" -> handleAddGroup
        "remove-task" -> handleRemoveTask
        "remove-group" -> handleRemoveGroup
        "mark-done" -> handleMarkDone
        "mark-pending" -> handleMarkPending
        "list-tasks" -> handleListTasks
        "list-groups" -> handleListGroups
        "help" -> handleHelp
        else -> handleNoMatch
    }
}
