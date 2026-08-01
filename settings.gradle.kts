pluginManagement {
    repositories {
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
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "b3-bem"

include(
    ":core",
    ":codec",
    ":protocol",
    ":sdk",
    ":server",
    ":sample",
)
