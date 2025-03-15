package ru.dezerom.core.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BooleanResponse(
    @Expose
    @SerializedName("response")
    val response: Boolean? = null,
)
