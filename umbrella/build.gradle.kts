plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)

            implementation(projects.features.auth.data)
            implementation(projects.features.auth.ui)

            implementation(projects.features.root.ui)
        }
    }
}