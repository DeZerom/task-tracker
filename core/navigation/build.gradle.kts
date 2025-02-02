plugins {
    alias(libs.plugins.build.android.core.ui.module)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ru.dezerom.core.navigation"
}

dependencies {
    implementation(libs.kotlin.serialization.json)
}