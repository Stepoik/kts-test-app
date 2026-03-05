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
include(":core:data")

include(":uikit")

include(":features:auth:ui")
include(":features:auth:entry")

include(":features:root:entry")
include(":features:root:ui")

include(":features:onboarding:entry")
include(":features:onboarding:ui")
include(":features:onboarding:api")
include(":features:onboarding:data")

include(":features:cources:ui")
include(":features:cources:api")
include(":features:cources:data")

include(":features:recommendations:entry")
include(":features:recommendations:ui")

include(":features:search:entry")
include(":features:search:ui")

include(":features:course-details:entry")
include(":features:course-details:ui")

include(":features:home:entry")
include(":features:home:ui")

include(":features:splash:entry")
include(":features:splash:ui")

include(":libs:lokksmith-compose")
include(":libs:lokksmith-core")