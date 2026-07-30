plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":codec"))

    testImplementation(kotlin("test"))
}
