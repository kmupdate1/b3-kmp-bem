import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.multiplatform)
    // alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
    jvm()

    iosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)

            api(project(":sdk"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
