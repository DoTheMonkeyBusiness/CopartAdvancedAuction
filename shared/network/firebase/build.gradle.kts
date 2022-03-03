import com.codingfeline.buildkonfig.compiler.FieldSpec
import extension.getString
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version Versions.serialization
    id("SharedLibraryPlugin")
    id("com.codingfeline.buildkonfig")
}

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.shared.base.auth)
                implementation(projects.shared.base.core)

                implementation(libs.koin.core)
                implementation(libs.kotlin.datetime)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.ktor.auth)
                implementation(libs.ktor.contentnegotiation)
                implementation(libs.ktor.core)
                implementation(libs.ktor.cio)
                implementation(libs.ktor.json)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.logging)
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.android)
            }
        }
        val androidTest by getting
        val desktopMain by getting {
            dependencies {
                implementation(libs.ktor.jvm)
            }
        }
        val desktopTest by getting
    }
}

buildkonfig {
    packageName = "com.cprt.advancedauction"
    objectName = "FirebaseNetworkConfig"
    defaultConfigs {
        buildConfigField(
            type = FieldSpec.Type.STRING,
            name = "FIREBASE_API_KEY",
            value = loadProperties("$rootDir/local.properties").getString("FIREBASE_API_KEY")
        )
    }
}
