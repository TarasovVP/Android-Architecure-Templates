
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    /*@OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        binaries.executable()
        browser()
    }*/

    sourceSets {
        //val wasmJsMain by getting
        commonMain.dependencies {
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            //Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(project(":shared"))
        }
        jsMain.dependencies {
            implementation(libs.koin.core)
            implementation(compose.html.core)
            implementation(compose.runtime)
        }
        /*wasmJsMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation("io.ktor:ktor-client-core:3.0.0-wasm1")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0-wasm1")
            implementation("io.ktor:ktor-client-content-negotiation:3.0.0-wasm1")
        }*/
    }
}

compose.experimental {
    web.application {}
}