import com.android.build.api.dsl.androidLibrary
import gradle.kotlin.dsl.accessors._2e4f3438bf96067bd815f32776db6f27.sourceSets
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        iosArm64()
        iosSimulatorArm64()

        androidLibrary {
            namespace = "com.example"

            experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
            compileSdk = libs.versions.android.compileSdk.get().toInt()

            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
            }
        }

        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.decompose.core)
            implementation(libs.decompose.compose)
        }
    }
}

kotlin {
    jvmToolchain(17)
}