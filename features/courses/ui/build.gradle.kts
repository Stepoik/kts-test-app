plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.uikit)
            implementation(projects.core.ui.compose)
            implementation(projects.core.ui.decompose)
            implementation(projects.features.courses.domain)
            implementation(projects.features.courses.api)

            implementation(libs.coil.compose)
            implementation(libs.koin.core)
        }
    }
}