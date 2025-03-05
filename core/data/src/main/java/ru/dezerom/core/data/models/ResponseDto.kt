package ru.dezerom.core.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @Expose
    @SerializedName("success")
    val success: Boolean,

    @Expose
    @SerializedName("body")
    val body: T,

    @Expose
    @SerializedName("error")
    val error: String? = null
)
