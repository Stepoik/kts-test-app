plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.splash.entry)
            implementation(projects.core.ui.compose)
            implementation(projects.uikit)

            implementation(libs.koin.core)
        }
    }
}