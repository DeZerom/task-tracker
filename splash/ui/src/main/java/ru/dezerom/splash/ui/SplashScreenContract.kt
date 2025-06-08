package ru.dezerom.splash.ui

class SplashScreenContract {
    sealed class SideEffect {
        data object GoToAuth: SideEffect()
        data object GoToTasks: SideEffect()
    }
}
