package ru.dezerom.navigation.api.destinations

import kotlinx.serialization.Serializable

@Serializable
data object TasksGraph

@Serializable
sealed class TasksDestinations {
    @Serializable
    data object TasksListDestination
}
