plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.ui.compose)
        }
    }
}