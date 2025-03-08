package ru.dezerom.app_build_logic.convention.utils.deps_sets

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope
import ru.dezerom.app_build_logic.convention.utils.androidTestImplementation
import ru.dezerom.app_build_logic.convention.utils.testImplementation

fun DependencyHandlerScope.testDependencies(libs: VersionCatalog) {
    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("kotlinx-coroutines-test").get())
}

fun DependencyHandlerScope.androidTestDependencies(libs: VersionCatalog) {
    testDependencies(libs)
    androidTestImplementation(libs.findLibrary("androidx-junit").get())
    androidTestImplementation(libs.findLibrary("androidx-espresso-core").get())
}
