plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.posts.api)

            implementation(libs.koin.core)
        }
    }
}