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
    ":codec",
    ":sdk",
    ":server",
    ":sample",
)
