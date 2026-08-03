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

            implementation(libs.foundation.runtime)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
