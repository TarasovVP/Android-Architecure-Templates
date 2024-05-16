plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "com.vnteam.architecturetemplates"
version = "1.0.0"
application {
    mainClass.set("com.vnteam.architecturetemplates.ApplicationKt")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.config.yaml)
    implementation("io.ktor:ktor-server-cors:2.3.11")
    testImplementation(libs.ktor.server.tests)
}
