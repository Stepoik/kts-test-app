plugins {
    id("multiplatform")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.poko)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-parameters")
    }

    androidLibrary {
        namespace = "dev.lokksmith.android"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlin.coroutines.core)
            api(libs.kotlinx.serialization.core)
            api(libs.kotlinx.serialization.json)
            api(libs.ktor.client.core)

            implementation(libs.datastore.preferences.core)
            implementation(libs.cryptography.core)
            implementation(libs.cryptography.provider.optimal)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.serialization.json)
        }

        androidMain.dependencies {
            api(libs.androidx.activity)
            api(libs.androidx.browser)
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
