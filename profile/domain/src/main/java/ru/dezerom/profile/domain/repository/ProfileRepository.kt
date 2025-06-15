package ru.dezerom.profile.domain.repository

import ru.dezerom.profile.domain.models.UserModel

interface ProfileRepository {
    suspend fun getMe(): Result<UserModel>
}