import com.android.build.api.dsl.androidLibrary
import gradle.kotlin.dsl.accessors._05cc99bc9a86cc2a07e4119f16b2547d.kotlin
import gradle.kotlin.dsl.accessors._05cc99bc9a86cc2a07e4119f16b2547d.sourceSets
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    androidLibrary {
        namespace = "com.example"

        compileSdk = libs.versions.android.compileSdk.get().toInt()

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)

            implementation(libs.decompose.core)
        }
    }
}

kotlin {
    jvmToolchain(17)
}