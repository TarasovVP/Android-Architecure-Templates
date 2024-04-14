buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.23")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}

plugins {
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
}