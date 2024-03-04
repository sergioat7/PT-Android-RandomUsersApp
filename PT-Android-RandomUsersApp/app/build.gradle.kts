plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

val appName = "com.aragones.sergio.randomusersapp"

android {

    namespace = appName
    compileSdk = 34

    defaultConfig {

        applicationId = appName
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
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

    implementation(project(":core:network"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.core.ktx)
            implementation(libs.appcompat)
            implementation(libs.material)
            implementation(libs.constraintlayout)
            implementation(libs.fragment.ktx)
            implementation(libs.lifecycle.livedata.ktx)
            implementation(libs.androidx.swiperefreshlayout)

            implementation(libs.hilt.android)
            kapt(libs.hilt.android.compiler)

            implementation(libs.picasso)

            implementation(libs.okhttp3.idling.resource)

            implementation(libs.bundles.navigation)

            testImplementation(libs.junit)
            testImplementation(libs.coroutines.test)
            testImplementation(libs.androidx.test)
            testImplementation(libs.mockitokotlin2)
            testImplementation(libs.mockito.inline)
            testImplementation(libs.mockito.core)

            androidTestImplementation(libs.androidx.test.ext.junit)
            androidTestImplementation(libs.espresso.core)
            androidTestImplementation(libs.barista) {
                exclude(group = "org.jetbrains.kotlin") // Only if you already use Kotlin in your project
            }
}