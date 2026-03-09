plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.onboarding.api)

            implementation(libs.datastore.preferences.core)
            implementation(libs.koin.core)
        }
    }
}