package ru.dezerom.app_build_logic.convention.utils.deps_sets

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.DependencyHandlerScope
import ru.dezerom.app_build_logic.convention.utils.implementation
import ru.dezerom.app_build_logic.convention.utils.ksp

internal fun PluginManager.coreDiPlugins() {
    apply("com.google.dagger.hilt.android")
    apply("com.google.devtools.ksp")
}

internal fun DependencyHandlerScope.coreDiDependencies(libs: VersionCatalog) {
    implementation(libs.findLibrary("google-dagger-hilt-android").get())
    ksp(libs.findLibrary("google-dagger-hilt-compiler").get())
}
