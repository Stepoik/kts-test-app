plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.search.entry)
            implementation(projects.core.ui.compose)
            implementation(projects.core.ui.decompose)
            implementation(projects.uikit)

            implementation(libs.koin.core)
        }
    }
}