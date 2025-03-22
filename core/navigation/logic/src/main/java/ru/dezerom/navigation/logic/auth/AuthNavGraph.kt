package ru.dezerom.navigation.logic.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.dezerom.navigation.api.destinations.AuthDestination
import ru.dezerom.navigation.api.destinations.RegistrationDestination
import ru.dezerom.ui.auth.AuthScreen
import ru.dezerom.ui.registration.RegistrationScreen

fun NavGraphBuilder.authNavGraph() {
    composable<AuthDestination> { AuthScreen() }
    composable<RegistrationDestination> { RegistrationScreen() }
}
