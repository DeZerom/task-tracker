package ru.dezerom.profile.data.repositories

import ru.dezerom.profile.data.mappers.toDomain
import ru.dezerom.profile.data.network.ProfileNetworkSource
import ru.dezerom.profile.domain.models.UserModel
import ru.dezerom.profile.domain.repository.ProfileRepository
import javax.inject.Inject

internal class ProfileRepositoryImpl @Inject constructor(
    private val networkSource: ProfileNetworkSource
): ProfileRepository {
    override suspend fun getMe(): Result<UserModel> {
        return networkSource.getMe().map { it.toDomain() }
    }
}