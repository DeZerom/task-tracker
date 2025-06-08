import ru.dezerom.app_build_logic.convention.utils.implementation

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.build.android.feature.ui.module)
}

android {
    namespace = "ru.dezerom.splash.ui"
    
    dependencies {
        implementation(project(":auth:domain"))
    }
}