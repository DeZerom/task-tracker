package ru.dezerom.app_build_logic.convention.utils

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.coreUiDependencies(libs: VersionCatalog) {
    //test
    testImplementation(libs.findLibrary("junit").get())
    androidTestImplementation(libs.findLibrary("androidx-junit").get())
    androidTestImplementation(libs.findLibrary("androidx-espresso-core").get())
    androidTestImplementation(libs.findLibrary("androidx-compose-bom").get())
    androidTestImplementation(libs.findLibrary("androidx-ui-test-junit4").get())

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
