
plugins {
    kotlin("multiplatform")
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
        androidMain.dependencies {
            // Koin
            implementation("io.insert-koin:koin-android:3.5.6")
            implementation("io.insert-koin:koin-androidx-compose:3.5.6")

            //Compose
            implementation(compose.material3)
            implementation("androidx.compose.material:material-ripple:1.7.0-alpha05")
            implementation("androidx.activity:activity-compose:1.9.0")
            implementation(project(":shared"))
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