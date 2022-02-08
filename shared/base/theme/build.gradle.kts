plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version Versions.composeJb
    id("SharedLibraryPlugin")
}

kotlin {
    android()
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
                implementation(compose.materialIconsExtended)
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
