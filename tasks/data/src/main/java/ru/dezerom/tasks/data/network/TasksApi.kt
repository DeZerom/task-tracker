package ru.dezerom.tasks.data.network

import retrofit2.http.GET
import retrofit2.http.POST
import ru.dezerom.core.data.models.BooleanResponse
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.tasks.data.network.models.CreateTaskNetworkDto
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

internal interface TasksApi {

    @GET("/tasks/all")
    suspend fun getAllTasks(): ResponseDto<TasksListNetworkDto?>

    @POST("tasks/create")
    suspend fun createTask(dto: CreateTaskNetworkDto): ResponseDto<BooleanResponse?>
}
