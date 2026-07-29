plugins {
    alias(libs.plugins.kotlin.multiplatform)
    id("org.bluebikebase.plugin.bem") version "0.1.0-SNAPSHOT"
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(project(":core"))
            api(project(":sdk"))
        }
        commonTest.dependencies {  }
    }
    sourceSets.commonTest.dependencies {
        implementation(kotlin("test"))
    }
}
