rootProject.name = "starter-service-client"

include("starter-service-models", "starter-service-kotlin-client")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
