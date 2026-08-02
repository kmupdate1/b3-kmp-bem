plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.status.pages)

    implementation(libs.mongodb.driver.kotlin.coroutine)

    implementation(libs.logback.classic)

    implementation(libs.b3.runtime.core)

    api(project(":model"))
    api(project(":protocol"))

    testImplementation(kotlin("test"))
}
