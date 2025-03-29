import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import ru.dezerom.app_build_logic.convention.utils.BuildConsts
import ru.dezerom.app_build_logic.convention.utils.configureKotlinAndroid
import ru.dezerom.app_build_logic.convention.utils.deps_sets.composeCompiler
import ru.dezerom.app_build_logic.convention.utils.deps_sets.coreDiDependencies
import ru.dezerom.app_build_logic.convention.utils.deps_sets.coreDiPlugins
import ru.dezerom.app_build_logic.convention.utils.deps_sets.coreUiDependencies
import ru.dezerom.app_build_logic.convention.utils.deps_sets.timberDependencies
import ru.dezerom.app_build_logic.convention.utils.implementation
import ru.dezerom.app_build_logic.convention.utils.libs

class FeatureUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                coreDiPlugins()
                composeCompiler()
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

            extensions.configure<KaptExtension> {
                correctErrorTypes = true
            }

            dependencies {
                implementation(project(":core:tools"))
                implementation(project(":core:ui"))
                implementation(project(":core:navigation:api"))

                coreUiDependencies(libs)
                coreDiDependencies(libs)
                timberDependencies(libs)

                //hilt view model
                implementation(libs.findLibrary("androidx-hilt-navigation").get())
            }
        }
    }
}
