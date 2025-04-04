plugins {
    kotlin("jvm")
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.pinterest.ktlint:ktlint-core:0.48.2")
    implementation(libs.kotlin.test)
}
