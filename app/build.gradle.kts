import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.application")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("org.jetbrains.compose") version "1.6.1"
    id("com.squareup.sqldelight")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            freeCompilerArgs += "-Xbinary=bundleId=com.vnteam.architecturetemplates.app"
            baseName = "app"
            isStatic = true
        }
    }
    task("testClasses")
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
            implementation(project(":shared"))
            //Voyager
            implementation("cafe.adriel.voyager:voyager-navigator:1.0.0")
            implementation("cafe.adriel.voyager:voyager-transitions:1.0.0")
            implementation("cafe.adriel.voyager:voyager-koin:1.0.0")
            // Koin
            implementation("io.insert-koin:koin-core:3.5.3")
            implementation("io.insert-koin:koin-compose:1.1.2")
        }
        androidMain.dependencies {
            implementation("androidx.core:core-ktx:1.12.0")
            implementation("androidx.appcompat:appcompat:1.6.1")
            implementation("com.google.android.material:material:1.11.0")
            implementation("androidx.constraintlayout:constraintlayout:2.1.4")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
            implementation(project(":shared"))

            //Lifecycle
            implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

            //Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

            //Gson
            implementation("com.google.code.gson:gson:2.10.1")

            //Coil
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")

            //Voyager
            implementation("cafe.adriel.voyager:voyager-navigator:1.0.0")
            implementation("cafe.adriel.voyager:voyager-transitions:1.0.0")
            implementation("cafe.adriel.voyager:voyager-koin:1.0.0")

            //SQLDelight`
            implementation("com.squareup.sqldelight:android-driver:1.5.5")
            implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")

            // Koin
            implementation("io.insert-koin:koin-android:3.5.3")
            implementation("io.insert-koin:koin-androidx-compose:3.5.3")

            //Ktor
            implementation("io.ktor:ktor-client-android:2.3.7")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")
            implementation("io.ktor:ktor-client-logging-jvm:2.3.7")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.7")

            //Compose
            implementation(platform("androidx.compose:compose-bom:2024.04.00"))
            implementation("androidx.compose.ui:ui")
            implementation("androidx.compose.ui:ui-tooling-preview")
            implementation("androidx.compose.material:material")
            implementation("androidx.compose.runtime:runtime")
            implementation("androidx.activity:activity-compose")
        }
        iosMain.dependencies {
            implementation(project(":shared"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation("io.insert-koin:koin-core:3.5.3")
            implementation("com.squareup.sqldelight:native-driver:1.5.5")
            implementation("io.ktor:ktor-client-darwin:2.3.7")
            //Coil
            implementation("io.coil-kt.coil3:coil-compose:3.0.0-alpha06")
            implementation("io.coil-kt.coil3:coil-network-ktor:3.0.0-alpha06")
            //Voyager
            implementation("cafe.adriel.voyager:voyager-navigator:1.0.0")
            implementation("cafe.adriel.voyager:voyager-transitions:1.0.0")
            implementation("cafe.adriel.voyager:voyager-koin:1.0.0")
        }
    }
}


android {
    namespace = "com.vnteam.architecturetemplates"
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.vnteam.architecturetemplates"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    compileSdk = 34
}

dependencies {

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}