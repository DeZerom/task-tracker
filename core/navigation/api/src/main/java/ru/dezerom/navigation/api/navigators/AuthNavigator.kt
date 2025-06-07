package ru.dezerom.navigation.api.navigators

interface AuthNavigator {
    fun fromAuthToRegistration()
    fun fromRegistrationToAuth()
    fun fromAuthToTasks()
}
