import com.codingfeline.buildkonfig.compiler.FieldSpec
import extension.getString
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform")
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

            }
        }
        val commonTest by getting
        val androidMain by getting
        val androidTest by getting
        val desktopMain by getting
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
