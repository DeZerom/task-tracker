class CoreToolsConventionPlugin {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                buildFeatures {
                    compose = true
                }

                composeOptions {
                    kotlinCompilerExtensionVersion = BuildConsts.KOTLIN_COMPILER_EXTENSION_VERSION
                }
            }

            dependencies {
                implementation(project(":core:tools"))
                implementation(project(":core:ui"))


            }
        }
    }
}