rootProject.name = "bar-service-kotlin-client"

include("bar-service-models", "bar-service-kotlin-stubs", "bar-service-client")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
