pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "b3-bem"

include(
    ":core",
    ":server",
    ":sdk",
    ":sample",
)
