plugins {
    id("multiplatform")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        commonMain.dependencies {
            api(libs.ktor.client.core)
            api(projects.libs.lokksmithCore)

            implementation(libs.datastore.preferences.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.koin.core)
            implementation(libs.napier)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}