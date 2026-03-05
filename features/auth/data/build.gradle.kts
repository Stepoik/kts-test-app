plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.api)
            implementation(projects.core.data)

            implementation(libs.datastore.preferences.core)
            implementation(libs.koin.core)
        }
    }
}