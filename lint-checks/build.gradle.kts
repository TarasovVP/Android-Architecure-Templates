plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    compileOnly(libs.lint.api)
    compileOnly(libs.lint.checks)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
