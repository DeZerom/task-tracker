import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.dezerom.app_build_logic.convention.utils.BuildConsts
import ru.dezerom.app_build_logic.convention.utils.androidTestImplementation
import ru.dezerom.app_build_logic.convention.utils.configureKotlinAndroid
import ru.dezerom.app_build_logic.convention.utils.debugImplementation
import ru.dezerom.app_build_logic.convention.utils.implementation
import ru.dezerom.app_build_logic.convention.utils.libs
import ru.dezerom.app_build_logic.convention.utils.testImplementation

class FeatureUiConventionPlugin: Plugin<Project> {
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
                implementation(project(":core:ui"))

                //test
                testImplementation(libs.findLibrary("junit").get())
                androidTestImplementation(libs.findLibrary("androidx-junit").get())
                androidTestImplementation(libs.findLibrary("androidx-espresso-core").get())
                androidTestImplementation(libs.findLibrary("androidx-compose-bom").get())
                androidTestImplementation(libs.findLibrary("androidx-ui-test-junit4").get())

                //debug
                debugImplementation(libs.findLibrary("androidx-ui-tooling").get())
                debugImplementation(libs.findLibrary("androidx-ui-test-manifest").get())

                //ui
                implementation(libs.findLibrary("androidx-core-ktx").get())
                implementation(libs.findLibrary("androidx-appcompat").get())
                implementation(libs.findLibrary("material").get())
                implementation(libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
                implementation(platform(libs.findLibrary("androidx-compose-bom").get()))
                implementation(libs.findLibrary("androidx-ui").get())
                implementation(libs.findLibrary("androidx-ui-graphics").get())
                implementation(libs.findLibrary("androidx-ui-tooling-preview").get())
                implementation(libs.findLibrary("androidx-material3").get())
            }
        }
    }
}
