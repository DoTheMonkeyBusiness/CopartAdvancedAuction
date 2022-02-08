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
                implementation(projects.shared.base.core)
                implementation(projects.shared.base.theme)

                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.ui)

                implementation(libs.voyager.navigator)
            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val desktopMain by getting
        val desktopTest by getting
    }
}
