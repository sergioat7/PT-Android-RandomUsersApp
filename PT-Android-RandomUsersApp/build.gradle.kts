// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${libs.versions.navigation.get()}")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}