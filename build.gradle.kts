import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.sqlDelight) apply false
    alias(libs.plugins.kotlinKover) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    id("org.sonarqube") version "6.1.0.5360"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlinx.kover")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
    dependencies {
        add("ktlint", project(":custom-ktlint-rules"))
    }

    apply(plugin = "io.gitlab.arturbosch.detekt")
    plugins.withId("io.gitlab.arturbosch.detekt") {
        configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
            source = files(
                "src/main/kotlin",
                "src/commonMain/kotlin",
                "src/jvmMain/kotlin",
                "src/androidMain/kotlin",
                "src/iosMain/kotlin",
                "src/nativeMain/kotlin",
                "src/desktop/kotlin",
                "src/js/kotlin",
            )
            config.setFrom(rootProject.file("detekt.yml"))
            buildUponDefaultConfig = true
            ignoreFailures = false
        }
    }
}

sonarqube {
    properties {
        property("sonar.kotlin.coveragePlugin", "kover")
        property("sonar.sourceEncoding", "UTF-8")
        property(
            "sonar.kotlin.coverage.reportPaths", "$rootDir/shared/build/reports/kover/xml/report.xml"
        )
        property(
            "sonar.coverage.jacoco.xmlReportPaths", "$rootDir/shared/build/reports/kover/xml/report.xml"
        )
    }
}

tasks.named("sonar") {
    dependsOn(provider {
        subprojects.map { it.tasks.named("koverXmlReport") }
    })
}

val installGitHook = tasks.register("installGitHook", Copy::class) {
    group = "git hooks"
    description = "Installs the pre-commit Git hook script"
    from("$rootDir/pre-commit")
    into("$rootDir/.git/hooks")
    fileMode = "755".toInt(8)
}

project.pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
    val kmpExtension = project.extensions.getByType<KotlinMultiplatformExtension>()
    kmpExtension.targets.configureEach {
        compilations.configureEach {
            compileKotlinTask.dependsOn(installGitHook)
        }
    }
}


