package ru.dezerom.navigation.api.destinations

import kotlinx.serialization.Serializable

@Serializable
data object AuthGraph

@Serializable
sealed class AuthDestinations {
    @Serializable
    data object AuthDestination

    @Serializable
    data object RegistrationDestination
}
