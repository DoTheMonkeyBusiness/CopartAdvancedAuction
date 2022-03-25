plugins {
    kotlin("multiplatform")
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
        val commonMain by getting {
            dependencies {
                implementation(projects.shared.base.core)
                implementation(projects.shared.base.security)

                implementation(libs.koin.core)
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.datastore)
                implementation(libs.koin.android)
            }
        }
        val androidTest by getting
        val desktopMain by getting
        val desktopTest by getting
    }
}
