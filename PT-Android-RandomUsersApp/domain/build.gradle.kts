plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {

    implementation(libs.moshi)
    kapt(libs.moshi.kotlin.codegen)
}