plugins {
    kotlin("jvm")
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.pinterest.ktlint:ktlint-core:0.49.1")
    testImplementation( "com.pinterest.ktlint:ktlint-test:0.48.2")
    implementation(libs.kotlin.test)
}
