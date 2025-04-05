plugins {
    alias(libs.plugins.build.android.feature.data.module)
}

android {
    namespace = "ru.dezerom.tasks.data"
}

dependencies {
    implementation(project(":core:interceptors"))
}
