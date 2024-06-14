plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)

    alias(libs.plugins.sqlDelight)
    application
}

group = "com.vnteam.architecturetemplates"
version = "1.0.0"
application {
    mainClass.set("com.vnteam.architecturetemplates.ApplicationKt")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.kotlinx.serialization.json)
    // Ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.postgresql)
    implementation(libs.hikari.cp)
    implementation(libs.jdbc.driver)
    testImplementation(libs.ktor.server.tests)
}

sqldelight {
    databases {
        create("ServerDatabase") {
            packageName = "com.vnteam.architecturetemplates"
            dialect("app.cash.sqldelight:postgresql-dialect:2.0.2")
        }
    }
}
