plugins {
    id("multiplatform-ui")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        commonMain.dependencies {
            api(projects.core.common)
            implementation(libs.coil.network.ktor3)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}