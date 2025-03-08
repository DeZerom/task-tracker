package ru.dezerom.app_build_logic.convention.utils.deps_sets

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope
import ru.dezerom.app_build_logic.convention.utils.debugImplementation
import ru.dezerom.app_build_logic.convention.utils.implementation

internal fun DependencyHandlerScope.coreUiDependencies(libs: VersionCatalog) {
    //test
    androidTestDependencies(libs)

    //debug
    debugImplementation(libs.findLibrary("androidx-ui-tooling").get())
    debugImplementation(libs.findLibrary("androidx-ui-test-manifest").get())

    //ui
    implementation(libs.findLibrary("androidx-core-ktx").get())
    implementation(libs.findLibrary("androidx-appcompat").get())
    implementation(libs.findLibrary("material").get())
    implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
    implementation(platform(libs.findLibrary("androidx-compose-bom").get()))
    implementation(libs.findLibrary("androidx-ui").get())
    implementation(libs.findLibrary("androidx-ui-graphics").get())
    implementation(libs.findLibrary("androidx-ui-tooling-preview").get())
    implementation(libs.findLibrary("androidx-material3").get())
    implementation(libs.findLibrary("androidx-navigation-compose").get())
}
