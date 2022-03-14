import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version libs.versions.kotlin.get()
    id("org.jetbrains.compose") version libs.versions.composeJb.get()
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
                implementation(projects.shared.base.resources)
                implementation(projects.shared.base.security)
                implementation(projects.shared.base.theme)

                implementation(projects.shared.feature.internalNotification)
                implementation(projects.shared.feature.logIn)
                implementation(projects.shared.feature.main)
                implementation(projects.shared.feature.onBoarding)
                implementation(projects.shared.feature.splash)

                implementation(projects.shared.network.firebase)

                implementation(projects.shared.storage.preferences)

                api(compose.ui)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(libs.koin.core)

                implementation(libs.kotlin.coroutines.core)
                implementation(libs.logger.kermit)
                implementation(libs.ktor.core)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.kotlin.datetime)
                implementation(libs.sqldelight.coroutines)
                implementation(libs.voyager.navigator)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.core)
                api(libs.koin.android)

                implementation(libs.ktor.android)
                implementation(libs.sqldelight.android)
            }
        }
        val androidTest by getting
        val desktopMain by getting {
            dependencies {
                api(compose.preview)

                implementation(libs.ktor.jvm)
                implementation(libs.sqldelight.jvm)
            }
        }
        val desktopTest by getting
    }
}
