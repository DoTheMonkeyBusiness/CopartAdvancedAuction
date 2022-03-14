pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "CopartAdvancedAuction"

include(":androidNative:android")

include(":desktopNative:desktop")

include(":shared:common")

include(":shared:base:auth")
include(":shared:base:core")
include(":shared:base:foundation")
include(":shared:base:resources")
include(":shared:base:security")
include(":shared:base:theme")

include(":shared:feature:internalNotification")
include(":shared:feature:logIn")
include(":shared:feature:main")
include(":shared:feature:onBoarding")
include(":shared:feature:splash")

include(":shared:network:firebase")

include(":shared:storage:preferences")
