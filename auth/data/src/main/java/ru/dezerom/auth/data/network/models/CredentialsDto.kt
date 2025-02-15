package ru.dezerom.auth.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class CredentialsDto(
    @Expose
    @SerializedName("login")
    val login: String,

    @Expose
    @SerializedName("password")
    val password: String
)
