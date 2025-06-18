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
        testInstrumentationRunner =
            "androidx.benchmark.selfinstrumenting.AndroidBenchmarkRunner"
        testInstrumentationRunnerArguments["androidx.benchmark.suppressErrors"] = "EMULATOR,LOW-BATTERY,DEBUGGABLE"
    }

    buildTypes {
        maybeCreate("benchmark").apply {
            isDebuggable = false
            isMinifyEnabled = false
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

    targetProjectPath = ":mobile"
    experimentalProperties["android.experimental.self-instrumenting"] = true
}

configurations.all {
    exclude(group = "com.google.errorprone", module = "error_prone_annotations")
}

dependencies {
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.uiautomator)
    implementation(libs.androidx.benchmark.macro.junit4)
    implementation("androidx.benchmark:benchmark-junit4:1.2.3")
    implementation("androidx.arch.core:core-runtime:2.2.0")
    implementation("androidx.profileinstaller:profileinstaller:1.3.1")
    implementation("androidx.startup:startup-runtime:1.1.1")
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