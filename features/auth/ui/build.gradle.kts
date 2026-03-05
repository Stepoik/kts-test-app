plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data)
            implementation(projects.features.auth.entry)
            implementation(projects.core.ui.compose)
            implementation(projects.uikit)
            implementation(projects.libs.lokksmithCompose)

            implementation(libs.koin.core)
        }
    }
}