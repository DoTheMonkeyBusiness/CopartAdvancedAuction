package plugins

import Versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import plugins.internal.libraryExtension

class SharedLibraryPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            applyLibraryPlugin()
            applyGroupNVersion()
            applyAndroidLibrarySection()
        }
    }
}

private fun Project.applyLibraryPlugin() {
    plugins.apply("com.android.library")
}

private fun Project.applyGroupNVersion() {
    group = "com.cprt.advancedauction"
    version = "1.0"
}

private fun Project.applyAndroidLibrarySection() = libraryExtension.run {
    compileSdk = Versions.Android.compileSDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.Android.minSDK
        targetSdk = Versions.Android.targetSDK
    }
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        unitTests.apply {
            unitTests.all {
                it.useJUnitPlatform()
            }
        }
    }
}
