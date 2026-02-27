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
include(":core:api")

include(":uikit")

include(":features:auth:ui")
include(":features:auth:entry")
include(":features:auth:api")
include(":features:auth:data")

include(":features:root:entry")
include(":features:root:ui")

include(":features:onboarding:entry")
include(":features:onboarding:ui")
include(":features:onboarding:api")
include(":features:onboarding:data")

include(":features:posts:entry")
include(":features:posts:ui")
include(":features:posts:api")
include(":features:posts:data")

include(":features:feed:entry")
include(":features:feed:ui")

include(":features:home:entry")
include(":features:home:ui")

include(":features:splash:entry")
include(":features:splash:ui")