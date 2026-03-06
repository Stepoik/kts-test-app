plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.recommendations.entry)

            implementation(projects.features.courses.ui)
            implementation(projects.features.courses.api)

            implementation(projects.core.ui.compose)
            implementation(projects.core.ui.decompose)
            implementation(projects.uikit)

            implementation(libs.koin.core)
        }
    }
}