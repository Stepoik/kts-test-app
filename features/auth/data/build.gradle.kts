plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.api)

            implementation(libs.datastore.preferences.core)
            implementation(libs.koin.core)
        }
    }
}