package ru.dezerom.profile.domain.dispatchers

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object LogoutDispatcher {
    private val _flow = MutableSharedFlow<LogoutEvents>()
    val flow = _flow.asSharedFlow()

    suspend fun dispatchEvent(event: LogoutEvents) {
        _flow.emit(event)
    }
}

sealed class LogoutEvents {
    data object Logout: LogoutEvents()
}