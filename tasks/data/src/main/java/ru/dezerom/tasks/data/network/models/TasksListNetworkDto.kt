package ru.dezerom.tasks.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TasksListNetworkDto(
    @Expose
    @SerializedName("tasks")
    val tasks: List<TaskNetworkDto> = emptyList()
)
