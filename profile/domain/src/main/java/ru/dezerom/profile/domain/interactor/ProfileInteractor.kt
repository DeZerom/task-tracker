package ru.dezerom.profile.domain.interactor

import ru.dezerom.auth.data.repositories.AuthRepository
import ru.dezerom.profile.domain.dispatchers.LogoutDispatcher
import ru.dezerom.profile.domain.dispatchers.LogoutEvents
import ru.dezerom.profile.domain.models.UserModel
import ru.dezerom.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val authRepository: AuthRepository
) {
    suspend fun getMe(): Result<UserModel> {
        return profileRepository.getMe()
    }

    suspend fun logout(): Result<Boolean> {
        return authRepository.unAuthorize().fold(
            onSuccess = {
                if (it) {
                    LogoutDispatcher.dispatchEvent(LogoutEvents.Logout)
                    Result.success(true)
                } else {
                    Result.success(false)
                }
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}