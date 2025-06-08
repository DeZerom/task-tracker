package ru.dezerom.navigation.api.destinations

import kotlinx.serialization.Serializable

@Serializable
object SplashGraph

@Serializable
sealed class SplashDestinations {
    @Serializable
    data object Splash
}