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
                implementation(projects.shared.base.core)
                implementation(projects.shared.base.navigation)

                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val desktopMain by getting
        val desktopTest by getting
    }
}
