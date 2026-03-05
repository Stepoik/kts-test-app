import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("multiplatform-ui")
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidLibrary {
        namespace = "dev.lokksmith.compose.android"
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.compose.ui)
            api(projects.libs.lokksmithCore)
        }

        androidMain.dependencies {
            api(libs.androidx.activity.compose)
            api(libs.androidx.browser)
        }
    }
}
