plugins {
    kotlin("jvm")
    `java-library`
}

dependencies {
    implementation("com.pinterest.ktlint:ktlint-rule-engine:1.0.0")
    implementation("com.pinterest.ktlint:ktlint-ruleset-standard:1.0.0")
    // testImplementation("com.pinterest.ktlint:ktlint-test:0.48.2")
    implementation(libs.kotlin.test)
}
