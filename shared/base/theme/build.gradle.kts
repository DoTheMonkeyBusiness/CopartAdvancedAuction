plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("SharedLibraryPlugin")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.material3)
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core)
            }
        }
        val androidTest by getting
        val desktopMain by getting
        val desktopTest by getting
    }
}
