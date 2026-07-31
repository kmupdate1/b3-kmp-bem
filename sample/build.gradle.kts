plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
    id("org.bluebikebase.plugin.bem") version "0.1.0-SNAPSHOT"
}

kotlin {
    jvm()

    iosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)

            api(project(":sdk"))
        }
        commonTest.dependencies {  }

        jvmMain.dependencies {
            implementation(libs.ktor.client.cio)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
    sourceSets.commonTest.dependencies {
        implementation(kotlin("test"))
    }
}
