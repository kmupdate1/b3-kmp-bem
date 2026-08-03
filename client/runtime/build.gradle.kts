plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()
    linuxArm64()
    iosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":sdk"))
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
