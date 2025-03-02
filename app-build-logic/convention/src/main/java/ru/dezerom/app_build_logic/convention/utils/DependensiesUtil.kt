package ru.dezerom.app_build_logic.convention.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun DependencyHandlerScope.implementation(dependencyNotation: Any) =
    add("implementation", dependencyNotation)

fun DependencyHandlerScope.testImplementation(dependencyNotation: Any) =
    add("testImplementation", dependencyNotation)

fun DependencyHandlerScope.androidTestImplementation(dependencyNotation: Any) =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandlerScope.debugImplementation(dependencyNotation: Any) =
    add("debugImplementation", dependencyNotation)

fun DependencyHandlerScope.ksp(dependencyNotation: Any) =
    add("ksp", dependencyNotation)

fun DependencyHandlerScope.kapt(dependencyNotation: Any) =
    add("kapt", dependencyNotation)
