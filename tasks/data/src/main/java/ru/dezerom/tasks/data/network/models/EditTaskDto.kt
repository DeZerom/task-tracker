package ru.dezerom.tasks.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EditTaskDto(
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("description")
    val description: String?,

    @Expose
    @SerializedName("deadline")
    val deadline: Long?,
)
