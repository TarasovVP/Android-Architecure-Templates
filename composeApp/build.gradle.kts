import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

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
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)
            dependencies {
                debugImplementation(libs.androidx.testManifest)
                implementation(libs.androidx.junit4)
            }
        }
    }
    task("testClasses")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            freeCompilerArgs += "-Xbinary=bundleId=com.vnteam.architecturetemplates.composeApp"
            linkerOpts.add("-lsqlite3")
            baseName = "composeApp"
            isStatic = true
        }
    }
    jvm("desktop")
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "webApp.js"
            }

        }
        binaries.executable()
    }
    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(libs.androidx.viewmodel.compose)
            //Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            //Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            //Navigation
            implementation(libs.navigation.compose)
            implementation(project(":shared"))
        }
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
        }
        iosMain.dependencies {
            implementation(libs.koin.core)
        }
        jsMain.dependencies {
            implementation(libs.koin.core)
            //Compose
            implementation(compose.html.core)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            //Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

      /*      implementation(npm("sql.js", "1.6.2"))
            implementation(devNpm("copy-webpack-plugin", "6.4.1"))*/
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
