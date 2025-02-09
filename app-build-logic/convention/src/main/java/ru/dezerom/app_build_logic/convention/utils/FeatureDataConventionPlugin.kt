package ru.dezerom.app_build_logic.convention.utils

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.dezerom.app_build_logic.convention.utils.deps_sets.apiDependencies
import ru.dezerom.app_build_logic.convention.utils.deps_sets.coreDiDependencies
import ru.dezerom.app_build_logic.convention.utils.deps_sets.coreDiPlugins
import ru.dezerom.app_build_logic.convention.utils.deps_sets.testDependencies

class FeatureDataConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                coreDiPlugins()
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            dependencies {
                testDependencies(libs)
                coreDiDependencies(libs)
                apiDependencies(libs)
            }
        }
    }

}
