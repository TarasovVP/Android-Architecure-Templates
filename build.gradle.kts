buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.46.1")
    }
}

plugins {
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
}