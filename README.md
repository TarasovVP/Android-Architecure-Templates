KMM Implementation

**Platform**: *Android, iOS*  
**Architecture**: *MVI*  
**Screens:** *(Android/ComponentActivity, iOS/ContentView) + @Composable*  
**Api:** *io.ktor.client.HttpClient*  
**Image loading:** *coil.compose.AsyncImage*   
**JsonConverter:** *kotlinx.serialization.SerialName*  
**DB:** *com.squareup.sqldelight.AppDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *kotlinx.coroutines.flow.StateFlow*  
**Navigation component:** *org.jetbrains.androidx.navigation*  
**DI:** *org.koin.core.KoinApplication*
