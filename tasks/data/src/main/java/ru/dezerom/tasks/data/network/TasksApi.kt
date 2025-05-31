package ru.dezerom.tasks.data.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import ru.dezerom.core.data.models.ResponseDto
import ru.dezerom.tasks.data.network.models.ChangeCompletedStatusNetworkDto
import ru.dezerom.tasks.data.network.models.CreateTaskNetworkDto
import ru.dezerom.tasks.data.network.models.TaskNetworkDto
import ru.dezerom.tasks.data.network.models.TasksListNetworkDto

internal interface TasksApi {

    @GET("/tasks/all")
    suspend fun getAllTasks(): ResponseDto<TasksListNetworkDto?>

    @POST("tasks/create")
    suspend fun createTask(@Body dto: CreateTaskNetworkDto): ResponseDto<TaskNetworkDto?>

    @PATCH("tasks/change_complete/{taskId}")
    suspend fun changeCompleteStatus(
        @Path("taskId") taskId: String
    ): ResponseDto<ChangeCompletedStatusNetworkDto?>
}
