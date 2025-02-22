package ru.dezerom.ui.auth

import androidx.lifecycle.ViewModel
import ru.dezerom.auth.domain.interactor.AuthInteractor

class AuthViewModel(
    private val authInteractor: AuthInteractor,
): ViewModel() {

}
