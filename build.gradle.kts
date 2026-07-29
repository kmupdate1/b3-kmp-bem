plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.plugin.serialization) apply false
}

group = "org.bluebikebase.bem"
version = "0.1.0-SNAPSHOT"

allprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        maven {
            url = uri("http://b3c-alpha-2:8081/repository/maven-public/")
            isAllowInsecureProtocol = true
        }
        mavenCentral()
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
                    val destination = "http://b3c-alpha-2:8081/repository"
                    val releases = "$destination/maven-releases/"
                    val snapshots = "$destination/maven-snapshots/"

                    url = uri(
                        if (version.toString().endsWith("SNAPSHOT")) snapshots
                        else releases
                    )
                    isAllowInsecureProtocol = true

                    credentials {
                        username = providers.environmentVariable("B3_REPO_USER").orNull
                        password = providers.environmentVariable("B3_REPO_PASS").orNull
                    }
                }
            }
        }
    }
}
