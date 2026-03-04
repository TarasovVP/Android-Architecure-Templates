[KMP with Test Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/kmp_with_tests)

**Platform**: *Android, iOS, Desktop(JVM), Web(Wasm), Web(JS), Server(Ktor+SqlDelight(dialect-PostgreSQL))*  
**Architecture**: *MVI (Model-View-Intent) + Clean Architecture*  
**Screens:** *(Android/ComponentActivity, iOS/ContentView, Desktop/androidx.compose.ui.window.Window, Web/org.jetbrains.compose.web.renderComposable) + @Composable*  

## Core Technologies
**Language**: *Kotlin*  
**UI Framework**: *Jetpack Compose Multiplatform*  
**Async**: *Kotlin Coroutines + StateFlow*  
**Serialization**: *Kotlinx Serialization*  

## Networking & Data
**API Client**: *io.ktor.client.HttpClient*  
**Image Loading**: *coil.compose.AsyncImage*  
**Database**: *com.squareup.sqldelight.AppDatabase*  
**Preferences**: *androidx.datastore.preferences*  

## Architecture Components
**ViewModel**: *androidx.lifecycle.ViewModel*  
**Navigation**: *org.jetbrains.androidx.navigation*  
**DI**: *org.koin.core.KoinApplication*  
**Multithreading**: *kotlinx.coroutines.CoroutineScope*  

## Testing & Quality
**Unit Testing**: *Kotlin Test, JUnit, Espresso*  
**Code Coverage**: *Kover*  
**Static Analysis**: *Detekt, Ktlint*  
**CI/CD**: *Jenkins, SonarQube*  
**Benchmarking**: *Androidx Benchmark*  

## Build & Development
**Gradle**: *Android Gradle Plugin*  
**Build Cache**: *Remote HTTP Build Cache*  
**Secrets Management**: *KMP Secrets Plugin*  
**Dependency Analysis**: *Gradle Dependency Analysis*  

## Server (Ktor)
**Server**: *Ktor Server + Netty*  
**Database**: *PostgreSQL + HikariCP*  
**Logging**: *Logback*  
**Config**: *Ktor Server Config YAML*