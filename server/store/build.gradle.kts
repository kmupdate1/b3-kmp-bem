plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.foundation.runtime)
    implementation(libs.foundation.ioe.http)
    implementation(libs.foundation.ioe.ktor)
    implementation(libs.foundation.ioe.mongo)
    implementation(libs.foundation.ioe.config)
    implementation(libs.foundation.ioe.logging)

    api(project(":model"))
    api(project(":protocol"))

    testImplementation(kotlin("test"))
}
