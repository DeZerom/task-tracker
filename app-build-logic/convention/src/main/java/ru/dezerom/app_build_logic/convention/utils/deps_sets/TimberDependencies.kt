package ru.dezerom.app_build_logic.convention.utils.deps_sets

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope
import ru.dezerom.app_build_logic.convention.utils.implementation

fun DependencyHandlerScope.timberDependencies(libs: VersionCatalog) {
    implementation(libs.findLibrary("jakewharton-timber").get())
}
