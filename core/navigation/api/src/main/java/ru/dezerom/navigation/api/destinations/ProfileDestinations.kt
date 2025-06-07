package ru.dezerom.navigation.api.destinations

import kotlinx.serialization.Serializable

@Serializable
object ProfileGraph

@Serializable
sealed class ProfileDestinations {
    @Serializable
    data object Profile: ProfileDestinations()
}