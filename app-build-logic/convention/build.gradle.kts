import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.example.starter.app_build_logic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "build.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        //tools
        register("coreToolsConventionPlugin") {
            id = "build.android.core.tools.module"
            implementationClass = "CoreToolsConventionPlugin"
        }

        //ui
        register("coreUiConventionPlugin") {
            id = "build.android.core.ui.module"
            implementationClass = "CoreUiConventionPlugin"
        }
        register("featureUiConventionPlugin") {
            id = "build.android.feature.ui.module"
            implementationClass = "FeatureUiConventionPlugin"
        }

        //domain
        register("featureDomainConventionPlugin") {
            id = "build.android.feature.domain.module"
            implementationClass = "FeatureDomainConventionPlugin"
        }

        //data
        register("coreDataConventionPlugin") {
            id = "build.android.core.data.module"
            implementationClass = "CoreDataConventionPlugin"
        }
        register("featureDataConventionPlugin") {
            id = "build.android.feature.data.module"
            implementationClass = "FeatureDataConventionPlugin"
        }
    }
}