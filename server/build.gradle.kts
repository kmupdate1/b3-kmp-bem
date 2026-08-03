plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.mongodb.driver.kotlin.coroutine)

    implementation(libs.logback.classic)

    implementation(libs.foundation.runtime)
    implementation(libs.foundation.ioe.http)
    implementation(libs.foundation.ioe.ktor)

    api(project(":model"))
    api(project(":protocol"))

    testImplementation(kotlin("test"))
}
