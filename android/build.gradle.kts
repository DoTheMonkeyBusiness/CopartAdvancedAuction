plugins {
    id("org.jetbrains.compose") version Versions.composeJb
    id("com.android.application")
    kotlin("android")
}

group = "com.cprt.advancedauction"
version = "1.0"

dependencies {
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.4.0")
}

android {
    compileSdk = Versions.Android.compileSDK
    defaultConfig {
        applicationId = "com.cprt.advancedauction.android"
        minSdk = Versions.Android.minSDK
        targetSdk = Versions.Android.targetSDK
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