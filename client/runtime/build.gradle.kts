plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()
    linuxArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":sdk"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
