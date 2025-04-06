plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation("com.pinterest.ktlint:ktlint-rule-engine:1.0.0")
    implementation("com.pinterest.ktlint:ktlint-ruleset-standard:1.0.0")
    implementation("com.pinterest.ktlint:ktlint-test:1.0.0")
    implementation(libs.kotlin.test)
}
