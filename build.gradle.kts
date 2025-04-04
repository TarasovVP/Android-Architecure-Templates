
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.kotlinKover) apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.2.0"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    repositories {
        mavenCentral()
    }
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}
