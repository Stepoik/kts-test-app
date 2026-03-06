plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.courses.api)
            api(projects.core.data)

            implementation(libs.koin.core)
        }
    }
}