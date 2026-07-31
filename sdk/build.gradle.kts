plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)

            api(project(":codec"))
            api(project(":protocol"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
