plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

val appName = "com.aragones.sergio.randomusersapp"

android {

    namespace = appName
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.core.ktx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.retrofit)

    implementation(libs.retrofit.converter.moshi)
    kapt(libs.moshi.kotlin.codegen)

    implementation(libs.okhttp3.idling.resource)
}