[KMP with Test Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/kmp_with_tests)

**Platform**: *Android, iOS, Desktop(JVM), Web(Wasm), Web(JS), Server(Ktor+SqlDelight(dialect-PostgreSQL))*  
**Architecture**: *MVI*  
**Screens:** *(Android/ComponentActivity, iOS/ContentView, Desktop/androidx.compose.ui.window.Window, Web/org.jetbrains.compose.web.renderComposable) + @Composable*  
**Api:** *io.ktor.client.HttpClient*  
**Image loading:** *coil.compose.AsyncImage*   
**JsonConverter:** *kotlinx.serialization.SerialName*  
**DB:** *com.squareup.sqldelight.AppDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *kotlinx.coroutines.flow.StateFlow*  
**Navigation component:** *org.jetbrains.androidx.navigation*  
**DI:** *org.koin.core.KoinApplication*