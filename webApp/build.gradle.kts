import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "webApp.js"
            }

        }
        binaries.executable()
    }

    /*@OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }*/
    task("testClasses")
    sourceSets {
        //val wasmJsMain by getting
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(libs.androidx.viewmodel.compose)
            //Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(project(":shared"))
        }
        jsMain.dependencies {
            implementation(libs.koin.core)
            implementation(compose.html.core)
            implementation(compose.runtime)
            implementation(npm("sql.js", "1.6.2"))
            implementation(devNpm("copy-webpack-plugin", "6.4.1"))
            //implementation(npm("react", "> 14.0.0 <=16.9.0"))
        }
        /*wasmJsMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation("io.ktor:ktor-client-core:3.0.0-wasm1")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0-wasm1")
            implementation("io.ktor:ktor-client-content-negotiation:3.0.0-wasm1")
        }*/
    }
}

rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin::class.java) {
    rootProject.the<YarnRootExtension>().yarnLockMismatchReport =
        YarnLockMismatchReport.WARNING // NONE | FAIL
    rootProject.the<YarnRootExtension>().reportNewYarnLock = false // true
    rootProject.the<YarnRootExtension>().yarnLockAutoReplace = false // true
}

/*
compose.experimental {
    web.application {}
}*/
