import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    task("testClasses")
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    js(IR) {
        useCommonJs()
        browser()
    }
    jvm()
    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            implementation(libs.stately.common)
            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.coroutines.core)
            implementation(compose.runtime)

            //Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            //SQLDelight
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines.extensions)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native.driver)
        }
        nativeMain.dependencies {
            implementation(libs.sqldelight.native.driver)
        }
        jvmMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.ktor.client.java)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.sqldelight.java.driver)
            implementation(libs.slf4j)
        }
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
            implementation(libs.sqldelight.js.driver)
            //implementation(npm("path-browserify", "1.0.1"))
            implementation(npm("sql.js", "1.6.2"))
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
        }
        /*wasmJsMain.dependencies {
            implementation("io.ktor:ktor-client-core:3.0.0-wasm1")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0-wasm1")
            implementation("io.ktor:ktor-client-content-negotiation:3.0.0-wasm1")
            implementation(libs.sqldelight.wasm.driver)
        }*/
    }
}

android {
    namespace = "com.vnteam.architecturetemplates.shared"
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.vnteam.architecturetemplates")
        }
    }
}

