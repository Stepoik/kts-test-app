plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.feed.entry)
            implementation(projects.features.posts.ui)
            implementation(projects.features.posts.api)
            implementation(projects.core.ui.compose)
            implementation(projects.uikit)

            implementation(libs.koin.core)
        }
    }
}