apply(plugin = BuildPlugins.UPDATE_DEPENDENCIES)

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    dependencies {
        classpath(libs.plugin.kotlin.gradle)
        classpath(libs.plugin.android.gradle)
        classpath(libs.plugin.sqldelight.gradle)
        classpath(libs.plugin.konfig.gradle)
    }
}

group = "com.cprt.advancedauction"
version = "1.0"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}