plugins {
    `maven-publish`
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm()

    iosArm64()

    sourceSets {
        commonMain.dependencies {  }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
