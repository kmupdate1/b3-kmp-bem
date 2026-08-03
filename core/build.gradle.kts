plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()
    linuxArm64()

    iosArm64()

    sourceSets {
        commonMain.dependencies {  }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
