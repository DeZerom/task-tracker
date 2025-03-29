package ru.dezerom.tasks.data.network

import retrofit2.http.GET
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

internal interface TasksApi {

    @GET("/tasks/all")
    suspend fun getAllTasks(): ResponseDto<TasksListNetworkDto?>

}
