package ru.dezerom.app_build_logic.convention.utils

import org.gradle.api.JavaVersion

internal object BuildConsts {
    const val TARGET_SDK = 35
    const val COMPILE_SDK = 35
    const val MIN_SDK = 28

    val JAVA_VERSION = JavaVersion.VERSION_1_8

    const val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.14"
}
