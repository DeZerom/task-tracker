import ru.dezerom.app_build_logic.convention.utils.implementation

plugins {
    alias(libs.plugins.build.android.feature.ui.module)
}

android {
    namespace = "ru.dezerom.ui"
}

dependencies {
    implementation(project(":auth:domain"))
}