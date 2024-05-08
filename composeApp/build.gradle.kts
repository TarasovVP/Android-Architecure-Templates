import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
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
    js(IR) {
        browser()
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }
    jvm("desktop")
    macosX64("macos") {
        binaries {
            executable {
                entryPoint = "main"
                baseName = "MyKMMApp"
            }
        }
    }
    macosArm64("macosArm") {
        binaries {
            executable {
                entryPoint = "main"
                baseName = "MyKMMApp"
            }
        }
    }

    sourceSets {
        val desktopMain by getting
        val wasmJsMain by getting
        androidMain.dependencies {
            implementation(libs.androidx.multidex)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            //Compose
            implementation(compose.material3)
            implementation(libs.androidx.activity.compose)
            implementation(libs.material.ripple)
        }
        desktopMain.dependencies {
            implementation(libs.koin.core)
            implementation(compose.desktop.currentOs)
            implementation(project(":shared"))
        }
        jsMain.dependencies {
            implementation(libs.koin.core)
            implementation(compose.html.core)
            implementation(compose.runtime)
            implementation(project(":shared"))
        }
        wasmJsMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(project(":shared"))
        }
    }
}


android {
    namespace = "com.vnteam.architecturetemplates"
    compileSdk = libs.versions.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.vnteam.architecturetemplates"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
}
dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.vnteam.architecturetemplates"
            packageVersion = "1.0.0"
        }
    }
}

compose.experimental {
    web.application {}
}