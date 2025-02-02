plugins {
    alias(libs.plugins.build.android.core.ui.module)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.dezerom.navigation.api"
}

dependencies {
    implementation(libs.kotlin.serialization.json)
}