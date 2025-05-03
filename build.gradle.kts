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
    alias(libs.plugins.sonarqube) apply true
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
        val koverReport = allprojects.mapNotNull { project ->
            val reportPath = "${project.projectDir}/build/reports/kover/report.xml"
            if (File(reportPath).exists()) reportPath else null
        }
            .joinToString(",")
        property("sonar.coverage.jacoco.xmlReportPaths", koverReport)
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


