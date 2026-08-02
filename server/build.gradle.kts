plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.status.pages)

    api(project(":codec"))
    api(project(":protocol"))

    testImplementation(kotlin("test"))
}
