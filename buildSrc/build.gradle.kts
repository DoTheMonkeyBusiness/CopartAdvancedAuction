plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("SharedLibraryPlugin") {
            id = "SharedLibraryPlugin"
            implementationClass = "plugins.SharedLibraryPlugin"
        }
    }
}

dependencies {
    implementation(libs.plugin.android.gradle)
    implementation(libs.plugin.kotlin.gradle)
    implementation(libs.plugin.versions.gradle)
}