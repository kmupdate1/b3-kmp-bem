plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.plugin.serialization) apply false
    alias(libs.plugins.kotlin.plugin.compose) apply false
    alias(libs.plugins.compose) apply false
}

group = "org.bluebikebase.bem"
version = "0.1.0-SNAPSHOT"

val profile = providers.gradleProperty("repo.profile").orElse("public")

val isPublic = profile.map { it == "public" }

val repoUrl = when (profile.get()) {
    "local" -> providers.gradleProperty("repo.url.local")
    "vpn" -> providers.gradleProperty("repo.url.vpn")
    else -> providers.gradleProperty("repo.url.public")
}

allprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("${repoUrl.orNull}/maven-public/")
            isAllowInsecureProtocol = !isPublic.get()
        }
    }

    configurations.configureEach {
        resolutionStrategy {
            cacheChangingModulesFor(0, "seconds")
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
            jvmToolchain(21)
        }
    }

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension> {
            jvmToolchain(21)
        }
    }

    plugins.withId("maven-publish") {
        publishing {
            repositories {
                maven {
                    val releases = "${repoUrl.orNull}/maven-releases/"
                    val snapshots = "${repoUrl.orNull}/maven-snapshots/"

                    url = uri(
                        if (version.toString().endsWith("SNAPSHOT")) snapshots
                        else releases
                    )
                    isAllowInsecureProtocol = !isPublic.get()

                    credentials {
                        username = providers.environmentVariable("B3_REPO_USER").orNull
                        password = providers.environmentVariable("B3_REPO_PASS").orNull
                    }
                }
            }
        }
    }
}
