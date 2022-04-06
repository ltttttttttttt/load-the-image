pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    
}
rootProject.name = "LoadTheImageSample"


include(":android")
include(":desktop")
include(":common")

include(":load-the-image")
