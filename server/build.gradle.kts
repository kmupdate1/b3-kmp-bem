plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(":codec"))
    api(project(":protocol"))

    testImplementation(kotlin("test"))
}
