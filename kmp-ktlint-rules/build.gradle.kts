plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    compileOnly("com.github.shyiko.ktlint:ktlint-core:0.27.0")
    implementation(libs.kotlin.test)
    testImplementation("com.github.shyiko.ktlint:ktlint-core:0.27.0")
    testImplementation("com.github.shyiko.ktlint:ktlint-test:0.27.0") {
        exclude(group = "com.andreapivetta.kolor", module = "kolor")
    }
}
