plugins {
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

dependencies {

    implementation(libs.moshi)
    kapt(libs.moshi.kotlin.codegen)
}