plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.build.android.feature.domain.module)
}

android {
    namespace = "ru.dezerom.profile.domain"
}