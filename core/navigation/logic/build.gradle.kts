plugins {
    alias(libs.plugins.build.android.core.ui.module)
}

android {
    namespace = "ru.dezerom.navigation.logic"
}

dependencies {
    implementation(project(":core:navigation:api"))

    implementation(project(":auth:ui"))
}