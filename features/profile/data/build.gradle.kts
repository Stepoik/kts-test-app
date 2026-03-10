plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.profile.api)
            api(projects.core.data)

            implementation(libs.koin.core)
        }
    }
}