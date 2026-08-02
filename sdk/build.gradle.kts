plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.serialization)
}

kotlin {
    jvm()

    iosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)

            implementation(libs.pdvrieze.xmlutil)

            api(project(":model"))
            api(project(":protocol"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
