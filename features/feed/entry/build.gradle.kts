plugins {
    id("multiplatform-ui-entry")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.ui.decompose)
        }
    }
}