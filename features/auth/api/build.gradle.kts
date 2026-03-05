plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.core.api)
            api(libs.lokksmith.core)
        }
    }
}