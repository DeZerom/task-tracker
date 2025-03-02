package ru.dezerom.app_build_logic.convention.utils.deps_sets

import org.gradle.api.plugins.PluginManager

internal fun PluginManager.composeCompiler() {
    apply("org.jetbrains.kotlin.plugin.compose")
}