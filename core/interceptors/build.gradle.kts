plugins {
    alias(libs.plugins.build.android.core.data.module)
}

android {
    namespace = "ru.dezerom.core.interceptors"
}

dependencies {
    implementation(project(":auth:data"))
}