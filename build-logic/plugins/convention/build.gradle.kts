plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.compose.multiplatform.gradle)
    implementation(libs.kotlin.multiplatform.gradle)
    implementation(libs.android.multiplatform.library.gradle)
    implementation(libs.kotlin.serialization.gradle)
    implementation(libs.compose.compiler.gradle)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}