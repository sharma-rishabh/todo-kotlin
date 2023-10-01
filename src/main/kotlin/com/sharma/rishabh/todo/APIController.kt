package com.sharma.rishabh.todo

import com.sharma.rishabh.todo.models.Task
import com.sharma.rishabh.todo.repositories.fs.FSRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

data class MutationResponse(
    val taskId: Int
)

data class MutateGroupsResponse(
    val status: Boolean
)

@RestController
@RequestMapping("/api/todo")
class APIController(
    @Autowired private val repo: FSRepository
) {
    @PostMapping("/add-task")
    fun addTask(@RequestBody addTaskRequest: AddTask):  MutationResponse {
        return MutationResponse(
            repo.addTask(addTaskRequest.groupName, addTaskRequest.taskDescription)
        )
    }

    @PostMapping("/remove-task")
    fun removeTask(@RequestBody mutateTaskRequest: MutateTask): MutationResponse {
        return MutationResponse(
            repo.removeTask(mutateTaskRequest.groupName, mutateTaskRequest.taskId)
        )
    }

    @PostMapping("/mark-done")
    fun markDone(@RequestBody mutateTaskRequest: MutateTask): MutationResponse {
        return MutationResponse(
            repo.markDone(mutateTaskRequest.groupName, mutateTaskRequest.taskId)
        )
    }

    @PostMapping("/mark-pending")
    fun markPending(@RequestBody mutateTaskRequest: MutateTask): MutationResponse {
        return MutationResponse(
            repo.markPending(mutateTaskRequest.groupName, mutateTaskRequest.taskId)
        )
    }

    @PostMapping("/add-group")
    fun addGroup(@RequestBody addGroupRequest: AddGroup): MutateGroupsResponse {
        return MutateGroupsResponse(
            repo.addGroup(addGroupRequest.groupName)
        )
    }

    @PostMapping("/remove-group")
    fun removeGroup(@RequestBody removeGroupRequest: AddGroup): MutateGroupsResponse {
        return MutateGroupsResponse(
            repo.removeGroup(removeGroupRequest.groupName)
        )
    }

    @GetMapping("/list-groups")
    fun listGroups(): MutableSet<String> {
        return repo.listGroups()
    }

    @GetMapping("/list-tasks/{groupName}")
    fun listTasks(@PathVariable groupName: String): List<Task> {
        return repo.listTasks(groupName)
    }
}
