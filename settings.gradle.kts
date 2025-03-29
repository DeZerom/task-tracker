pluginManagement {
    includeBuild("app-build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TaskTracker"
include(":app")
include(":auth")
include(":auth:ui")
include(":core")
include(":core:ui")
include(":core:tools")
include(":core:navigation")
include(":core:navigation:logic")
include(":core:navigation:api")
include(":auth:domain")
include(":auth:data")
include(":core:data")
include(":tasks")
include(":tasks:ui")
