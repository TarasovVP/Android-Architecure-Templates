buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.23")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}

plugins {
    id("org.jetbrains.kotlin.android") version "1.9.23" apply false
    id("com.android.application") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
}