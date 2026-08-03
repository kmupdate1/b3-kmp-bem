pluginManagement {
    repositories {
        mavenLocal()
        maven {
            val profile = providers.gradleProperty("repo.profile").orElse("public")

            val isPublic = profile.map { it == "public" }

            val repoUrl = when (profile.get()) {
                "local" -> providers.gradleProperty("repo.url.local")
                "vpn" -> providers.gradleProperty("repo.url.vpn")
                else -> providers.gradleProperty("repo.url.public")
            }

            url = uri("${repoUrl.orNull}/maven-public/")
            isAllowInsecureProtocol = !isPublic.get()
        }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

rootProject.name = "b3-bem"

include(
    ":core",
    ":model",
    ":protocol",
    ":sdk",
    ":dsl",

    ":client:runtime",

    "editor-core",
    "editor-yaml",
    "editor-compose",

    ":server:store",
    ":server:analysis",

    ":samples:runtime",
    ":samples:client",
    ":samples:server",
)
