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
                implementation(projects.shared.base.auth)
                implementation(projects.shared.base.core)
                implementation(projects.shared.base.dateTime)
                implementation(projects.shared.base.navigation)
                implementation(projects.shared.base.resources)

                implementation(projects.shared.network.firebase)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)

                implementation(libs.kotlin.coroutines.core)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val desktopMain by getting
        val desktopTest by getting
    }
}
