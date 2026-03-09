plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.datastore.preferences.core)
            implementation(libs.koin.core)
        }
    }
}