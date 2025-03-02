import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import ru.dezerom.app_build_logic.convention.utils.BuildConsts
import ru.dezerom.app_build_logic.convention.utils.configureKotlinAndroid
import ru.dezerom.app_build_logic.convention.utils.deps_sets.composeCompiler
import ru.dezerom.app_build_logic.convention.utils.deps_sets.coreUiDependencies
import ru.dezerom.app_build_logic.convention.utils.implementation
import ru.dezerom.app_build_logic.convention.utils.libs

class CoreUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
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

            dependencies {
                implementation(project(":core:tools"))

                coreUiDependencies(libs)
            }
        }
    }
}
