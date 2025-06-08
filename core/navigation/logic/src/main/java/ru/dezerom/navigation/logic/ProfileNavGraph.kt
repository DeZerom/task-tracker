package ru.dezerom.navigation.logic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.dezerom.navigation.api.destinations.ProfileDestinations
import ru.dezerom.navigation.api.destinations.ProfileGraph
import ru.dezerom.profile.ui.ProfileScreen

fun NavGraphBuilder.profileNavGraph() {
    navigation<ProfileGraph>(
        startDestination = ProfileDestinations.Profile,
        builder = { composable<ProfileDestinations.Profile> { ProfileScreen() }
        }
    )
}