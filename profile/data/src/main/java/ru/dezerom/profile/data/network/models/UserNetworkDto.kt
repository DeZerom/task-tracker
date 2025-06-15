package ru.dezerom.profile.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserNetworkDto(
    @Expose
    @SerializedName("id")
    val id: String? = null,

    @Expose
    @SerializedName("login")
    val login: String? = null,

    @Expose
    @SerializedName("tasks")
    val tasks: Int? = null,

    @Expose
    @SerializedName("completedTasks")
    val completedTasks: Int? = null,

    @Expose
    @SerializedName("uncompletedTasks")
    val uncompletedTasks: Int? = null,
)