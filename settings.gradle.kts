rootProject.name = "{TEMPLATE_SERVICE_HYPHEN_NAME}-service-client"

include("{TEMPLATE_SERVICE_HYPHEN_NAME}-service-models", "{TEMPLATE_SERVICE_HYPHEN_NAME}-service-kotlin-client")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}
