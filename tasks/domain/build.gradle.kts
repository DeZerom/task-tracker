plugins {
    alias(libs.plugins.build.android.feature.domain.module)
}

android {
    namespace = "ru.dezerom.tasks.domain"
}

dependencies {
    implementation(project(":tasks:data"))
}