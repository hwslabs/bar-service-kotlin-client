rootProject.name = "bar-service-kotlin-client"

include("starter-service-models", "starter-service-client")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
