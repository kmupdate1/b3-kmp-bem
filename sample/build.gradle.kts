plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("org.bluebikebase.plugin.bem") version "0.1.0-SNAPSHOT"
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.darwin)

            api(project(":sdk"))
        }
        commonTest.dependencies {  }
    }
    sourceSets.commonTest.dependencies {
        implementation(kotlin("test"))
    }
}
