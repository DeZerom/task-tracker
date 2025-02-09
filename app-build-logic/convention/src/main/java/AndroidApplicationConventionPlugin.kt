import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.dezerom.app_build_logic.convention.utils.deps_sets.coreUiDependencies
import ru.dezerom.app_build_logic.convention.utils.implementation
import ru.dezerom.app_build_logic.convention.utils.libs

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(project(":core:tools"))
                implementation(project(":core:ui"))
                implementation(project(":core:navigation:logic"))
                implementation(project(":core:navigation:api"))

                implementation(project(":auth:ui"))

                coreUiDependencies(libs)
            }
        }
    }
}