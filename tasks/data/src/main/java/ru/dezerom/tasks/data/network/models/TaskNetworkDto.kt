package ru.dezerom.tasks.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TaskNetworkDto(
    @Expose
    @SerializedName("id")
    val id: String? = null,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("description")
    val description: String? = null,

    @Expose
    @SerializedName("deadline")
    val deadline: Long? = null,

    @Expose
    @SerializedName("created_at")
    val createdAt: Long? = null,

    @Expose
    @SerializedName("is_completed")
    val isCompleted: Boolean? = null,

    @Expose
    @SerializedName("completed_at")
    val completedAt: Long? = null,
)
