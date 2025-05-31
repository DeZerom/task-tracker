package ru.dezerom.tasks.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChangeCompletedStatusNetworkDto(
    @Expose
    @SerializedName("success")
    val success: Boolean? = null,

    @Expose
    @SerializedName("completedAt")
    val completedAt: Long? = null,
)