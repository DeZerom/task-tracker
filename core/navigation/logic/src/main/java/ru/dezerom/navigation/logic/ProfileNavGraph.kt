package ru.dezerom.navigation.logic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.navigation.api.destinations.ProfileDestinations
import ru.dezerom.navigation.api.destinations.ProfileGraph

fun NavGraphBuilder.profileNavGraph() {
    navigation<ProfileGraph>(
        startDestination = ProfileDestinations.Profile,
        builder = {
            composable<ProfileDestinations.Profile> {
                Box(modifier = Modifier.size(40.dp).background(color = Colors.error))
            }
        }
    )
}