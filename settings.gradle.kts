rootProject.name = "TestApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":androidApp")
include(":umbrella")

include(":core:ui:decompose")
include(":core:ui:compose")

include(":uikit")

include(":features:auth:ui")
include(":features:auth:entry")
include(":features:auth:api")
include(":features:auth:data")

include(":features:root:entry")
include(":features:root:ui")

include(":features:onboarding:entry")
include(":features:onboarding:ui")

include(":features:main:entry")
include(":features:main:ui")

include(":features:home:entry")
include(":features:home:ui")