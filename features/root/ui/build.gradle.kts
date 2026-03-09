plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.root.entry)
            implementation(projects.features.auth.entry)
            implementation(projects.features.splash.entry)
            implementation(projects.features.onboarding.entry)
            implementation(projects.features.home.entry)

            implementation(projects.features.onboarding.api)
            implementation(projects.features.auth.api)

            implementation(libs.koin.core)
        }
    }
}