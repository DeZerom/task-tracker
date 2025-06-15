package ru.dezerom.profile.data.network

import ru.dezerom.core.data.utils.safeApiCall
import ru.dezerom.profile.data.mappers.toDataModel
import ru.dezerom.profile.data.models.UserDataModel
import javax.inject.Inject

internal class ProfileNetworkSource @Inject constructor(
    private val api: ProfileApi
) {
    suspend fun getMe(): Result<UserDataModel> {
        return safeApiCall {
            api.getMe()
        }.map { it.toDataModel() }
    }
}