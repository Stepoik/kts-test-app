plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(projects.core.data)

            implementation(projects.features.auth.ui)

            implementation(projects.features.onboarding.data)
            implementation(projects.features.onboarding.ui)

            implementation(projects.features.courses.ui)
            implementation(projects.features.courses.data)

            implementation(projects.features.courseDetails.ui)

            implementation(projects.features.recommendations.ui)

            implementation(projects.features.search.ui)

            implementation(projects.features.splash.ui)

            implementation(projects.features.home.ui)

            implementation(projects.features.root.ui)
        }
    }
}