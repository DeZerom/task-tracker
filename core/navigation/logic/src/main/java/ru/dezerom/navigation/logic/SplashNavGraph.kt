package ru.dezerom.navigation.logic

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.dezerom.navigation.api.destinations.SplashDestinations
import ru.dezerom.navigation.api.destinations.SplashGraph
import ru.dezerom.navigation.logic.navigators.SplashNavigatorImpl
import ru.dezerom.splash.ui.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navController: NavController
) {
    navigation<SplashGraph>(
        startDestination = SplashDestinations.Splash,
        builder = {
            composable<SplashDestinations.Splash> { SplashScreen(SplashNavigatorImpl(navController)) }
        }
    )
}