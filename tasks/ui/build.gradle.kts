plugins {
    alias(libs.plugins.build.android.feature.ui.module)
}

android {
    namespace = "ru.dezerom.tasks.ui"
}

dependencies {
    implementation(project(":tasks:domain"))
}