plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.build.android.feature.data.module)
}

android {
    namespace = "ru.dezerom.profile.data"
}

dependencies {
    implementation(project(":core:interceptors"))
    implementation(project(":profile:domain"))
}