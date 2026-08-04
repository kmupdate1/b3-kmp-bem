plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("org.bluebikebase.plugin.bem") version "0.1.0-SNAPSHOT"
}

kotlin {
    jvm()
    linuxArm64()
    iosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)

            implementation(libs.foundation.runtime)
            implementation(libs.foundation.ioe.ktor.client)
            implementation(libs.foundation.ioe.config)
            implementation(libs.foundation.ioe.logging)

            implementation(project(":dsl"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

tasks.register("regenerate") {
    description = ""
    group = "bem"

    dependsOn(":client:agent:clean")
    finalizedBy(":client:agent:generateBem")
}
