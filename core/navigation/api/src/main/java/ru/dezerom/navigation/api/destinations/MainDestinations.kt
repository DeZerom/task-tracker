package ru.dezerom.navigation.api.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import ru.dezerom.core.tools.R
import ru.dezerom.core.tools.string_container.StringContainer
import ru.dezerom.core.tools.string_container.toStringContainer

@Serializable
object MainGraph

@Serializable
sealed class MainDestinations {
    abstract val route: String
    abstract val icon: ImageVector
    abstract val name: StringContainer

    @Serializable
    data object Tasks: MainDestinations() {
        override val route: String = "tasksRootRoute"
        override val icon: ImageVector = Icons.Default.Check
        override val name: StringContainer = R.string.tasks.toStringContainer()
    }

    @Serializable
    data object Profile: MainDestinations() {
        override val route: String = "profileRootRoute"
        override val icon: ImageVector = Icons.Default.Person
        override val name: StringContainer = R.string.profile.toStringContainer()
    }

    companion object {
        val items = listOf(Tasks, Profile)
    }
}