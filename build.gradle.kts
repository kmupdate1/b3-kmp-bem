plugins {
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.plugin.serialization) apply false
}

group = "org.bluebikebase.bem"
version = "0.1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}
