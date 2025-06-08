package ru.dezerom.profile.ui

class ProfileScreenContract {
    sealed class Event {
        data object Logout: Event()
    }
}