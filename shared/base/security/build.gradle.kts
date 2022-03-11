import com.codingfeline.buildkonfig.compiler.FieldSpec
import extension.getString
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform")
    id("SharedLibraryPlugin")
    id("com.codingfeline.buildkonfig")
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
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.test.kotlin.junit5)
                implementation(libs.test.junit.jupiter.params)
                implementation(libs.test.mockk)
            }
        }
        val androidMain by getting
        val androidTest by getting
        val desktopMain by getting
        val desktopTest by getting
    }
}

buildkonfig {
    packageName = "com.cprt.advancedauction"
    objectName = "SecurityConfig"
    defaultConfigs {
        buildConfigField(
            type = FieldSpec.Type.STRING,
            name = "SECRET_SALT_KEY",
            value = loadProperties("$rootDir/local.properties").getString("SECRET_SALT_KEY")
        )
    }
}
