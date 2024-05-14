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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

>>>>>>> c06ca37f6b90fd49d15a73383d6b614e132cb81f
>>>>>>> 478ae2cf83a1416e08dfd8df85fc69d63b4f5945
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "teachandlearn"
include(":app")
