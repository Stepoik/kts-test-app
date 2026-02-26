plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.root.entry)
            implementation(projects.features.auth.entry)

            implementation(libs.koin.core)
        }
    }
}