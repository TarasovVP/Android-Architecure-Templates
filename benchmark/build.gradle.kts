import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
    id(libs.plugins.androidTest.get().pluginId)
    kotlin("android")
}

android {
    namespace = "com.vnteam.benchmark"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "EMULATOR,LOW-BATTERY,DEBUGGABLE"
    }

    buildTypes {
        create("benchmark") {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    testOptions {
        managedDevices {
            allDevices {
                maybeCreate<ManagedVirtualDevice>("pixel4Api33").apply {
                    device = "Pixel 4"
                    apiLevel = 33
                    systemImageSource = "google"
                    require64Bit = true
                }
                emulatorSnapshots {
                    enableForTestFailures = false
                    maxSnapshotsForTestFailures = 0
                }
            }
        }
    }

    targetProjectPath = ":mobile"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.uiautomator)
    implementation(libs.androidx.benchmark.macro.junit4)
}

androidComponents {
    beforeVariants { variant ->
        variant.enable = variant.buildType == "benchmark"
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvmVersion.get()))
    }
}