import ru.dezerom.app_build_logic.convention.utils.implementation

plugins {
    alias(libs.plugins.build.android.feature.domain.module)
}

android {
    namespace = "ru.dezerom.auth.domain"
}

dependencies {
    implementation(project(":auth:data"))
}