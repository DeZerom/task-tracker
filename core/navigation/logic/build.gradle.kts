import ru.dezerom.app_build_logic.convention.utils.implementation

plugins {
    alias(libs.plugins.build.android.core.ui.module)
}

android {
    namespace = "ru.dezerom.navigation.logic"
}

dependencies {
    implementation(project(":core:navigation:api"))

    implementation(project(":splash:ui"))
    implementation(project(":auth:ui"))
    implementation(project(":tasks:ui"))
    implementation(project(":profile:ui"))
}