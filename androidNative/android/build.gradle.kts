plugins {
    id("org.jetbrains.compose") version libs.versions.composeJb.get()
    id("com.android.application")
    kotlin("android")
}

group = "com.cprt.advancedauction"
version = "1.0"

dependencies {
    implementation(projects.shared.common)
    implementation(libs.compose.android.activity)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.cprt.advancedauction.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}