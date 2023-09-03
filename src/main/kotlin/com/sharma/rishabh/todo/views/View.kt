package com.sharma.rishabh.todo.views

import com.sharma.rishabh.todo.models.Task

class View(private val printer: (String) -> Unit) {
    fun showAddTaskResponse(response: Int, groupName: String) {
        if (response < 0) {
            printer("Couldn't add task. Make sure $groupName exists.")
            return
        }
        printer("Task added to group $groupName with ID $response")
    }

    fun showRemoveTaskResponse(id: Int, response: Int, groupName: String) {
        if (response < 0) {
            printer("Couldn't remove task. Make sure $groupName and the task ID $id exists.")
            return
        }
        printer("Task with ID $response removed from $groupName")
    }

    fun showAddGroupResponse(response: Boolean, groupName: String) {
        if (!response) {
            printer("Couldn't add group $groupName. Try again!")
            return
        }
        printer("Group $groupName added successfully")
    }

    fun showRemoveGroupResponse(response: Boolean, groupName: String) {
        if (!response) {
            printer("Couldn't remove group $groupName. Try again! Make sure it exists")
        }
        printer("Group $groupName removed successfully")
    }

    fun showMarkDoneResponse(id: Int, response: Int, groupName: String) {
        if (response < 0) {
            printer("Couldn't mark task done. Make sure $groupName and the task ID $id exists.")
            return
        }
        printer("Task with ID $response marked done in $groupName")
    }

    fun showMarkPendingResponse(id: Int, response: Int, groupName: String) {
        if (response < 0) {
            printer("Couldn't mark task pending. Make sure $groupName and the task ID $id exists.")
            return
        }
        printer("Task with ID $response marked pending in $groupName")
    }

    fun showListTasksResponse(response: List<Task>, groupName: String) {
        if (response.isEmpty()) {
            printer("No tasks to show for group $groupName.")
            return
        }
        var message = "${groupName.uppercase()} \n"
        response.forEach {
            var symbol = "⏳"
            if (it.isDone) {
                symbol = "✅"
            }
            message += "$it.id \t $symbol \t ${it.description} \n"
        }
        printer(message)
    }

    fun showListGroupResponse(groups: MutableSet<String>) {
        var message = "Groups: \n"
        groups.forEach {
            message += "$it \n"
        }

        printer(message)
    }

    fun showNoMatchResponse(command: String) {
        printer("$command not found. Incorrect options")
        showHelpResponse()
    }

    fun showHelpResponse() {
        val message = """Commands
[add-group group-name]                      To add a new group to TODO
[add-task  group-name task-description]     To create a task under a group
[done group-name task-id]                   To mark a task done.
[pending group-name task-id]                To mark a task pending.
[remove-task group-name task-id]            To remove a particular task id from a group
[remove-group group-name]                   To remove a group and all tasks under it
[list-tasks group-name]                     To see a list of all the tasks under a group
[list-groups]                               To see a list of groups in your todo
[help]                                      To see this help message"""
        printer(message)
    }
}
