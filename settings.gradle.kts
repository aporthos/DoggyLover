pluginManagement {
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

rootProject.name = "DoggyLover"
include(":app")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:domain")
include(":core:network")
include(":core:models:entity")
include(":core:models:network")
include(":core:models:domain")
include(":core:designsystem")
include(":core:ui")
include(":feature:favorites")
include(":feature:dogs")
include(":feature:onboarding")
include(":core:models:ui")
