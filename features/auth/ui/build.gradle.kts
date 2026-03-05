plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data)
            implementation(projects.features.auth.entry)
            implementation(projects.features.auth.api)
            implementation(projects.core.ui.compose)
            implementation(projects.uikit)

            implementation(libs.koin.core)
            implementation(libs.lokksmith.compose)
        }
    }
}