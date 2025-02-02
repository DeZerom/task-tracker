package ru.dezerom.navigation.logic.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.dezerom.core.tools.consts.Colors
import ru.dezerom.navigation.api.destinations.AuthDestination
import ru.dezerom.navigation.api.destinations.RegistrationDestination
import ru.dezerom.ui.screens.AuthScreen

fun NavGraphBuilder.authNavGraph() {
    composable<AuthDestination> { AuthScreen() }
    composable<RegistrationDestination> { Box(modifier = Modifier.fillMaxSize().background(Colors.accent)) }
}
