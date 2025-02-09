package ru.dezerom.app_build_logic.convention.utils.deps_sets

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope
import ru.dezerom.app_build_logic.convention.utils.implementation

fun PluginManager.serializationPlugins() {
    apply("org.jetbrains.kotlin.plugin.serialization")
}

fun DependencyHandlerScope.serializationDependencies(libs: VersionCatalog) {
    implementation(libs.findLibrary("kotlin-serialization-json").get())
}
