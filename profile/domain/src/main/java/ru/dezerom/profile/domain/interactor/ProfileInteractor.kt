package ru.dezerom.profile.domain.interactor

import ru.dezerom.profile.domain.models.UserModel
import ru.dezerom.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileInteractor @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun getMe(): Result<UserModel> {
        return profileRepository.getMe()
    }
}