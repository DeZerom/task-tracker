package ru.dezerom.navigation.api.tools

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

val LocalNavController = compositionLocalOf<NavController> { error("Not yet init") }
