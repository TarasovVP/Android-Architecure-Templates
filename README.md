The project includes templates for main architectural solutions and examples of using different approaches to specific functional implementations.   
It consists of two screens. On the first one, a list of data is obtained, which, after caching, is displayed in a list format. Clicking on a selected item launches the next screen with detailed information. To view the required implementation, you need to switch to the corresponding branch.   
Below is a list of branches with an overview of the applied approaches:

[KMP Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/kmp_implementation)  

**Platform**: *Android, IOS, Desktop(JVM), Web(JS)*  
**Architecture**: *MVI*  
**Screens:** *(Android/ComponentActivity, IOS/ContentView, Desktop/androidx.compose.ui.window.Window, Web/org.jetbrains.compose.web.renderComposable) + @Composable*  
**Api:** *io.ktor.client.HttpClient*  
**Image loading:** *coil.compose.AsyncImage*   
**JsonConverter:** *kotlinx.serialization.SerialName*  
**DB:** *com.squareup.sqldelight.AppDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *kotlinx.coroutines.flow.StateFlow*  
**Navigation component:** *org.jetbrains.androidx.navigation*  
**DI:** *org.koin.core.KoinApplication*  

[KMM Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/kmm_implementation)

**Platform**: *Android, IOS*  
**Architecture**: *MVI*  
**Screens:** *(Android/ComponentActivity, IOS/ContentView) + @Composable*  
**Api:** *io.ktor.client.HttpClient*  
**Image loading:** *coil.compose.AsyncImage*   
**JsonConverter:** *kotlinx.serialization.SerialName*  
**DB:** *com.squareup.sqldelight.AppDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *kotlinx.coroutines.flow.StateFlow*  
**Navigation component:** *org.jetbrains.androidx.navigation*  
**DI:** *org.koin.core.KoinApplication*  

[MVI with Compose Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvi_compose_navigation_implementation)

**Architecture**: *MVI*  
**Screens:** *androidx.appcompat.app.AppCompatActivity + @Composable*  
**Api:** *io.ktor.client.HttpClient*  
**Image loading:** *coil.compose.AsyncImage*   
**JsonConverter:** *kotlinx.serialization.SerialName*  
**DB:** *com.squareup.sqldelight.AppDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *kotlinx.coroutines.flow.StateFlow*  
**Navigation component:** *cafe.adriel.voyager.navigator.Navigator*  
**DI:** *org.koin.core.KoinApplication*  


[MVVM with Compose Navigation Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvvm_compose_navigation_implementation)

**Architecture:** *MVVM*    
**Screens:** *androidx.appcompat.app.AppCompatActivity + @Composable*   
**Api:** *io.ktor.client.HttpClient*  
**Image loading:** *coil.compose.AsyncImage*   
**JsonConverter:** *kotlinx.serialization.SerialName*  
**DB:** *com.squareup.sqldelight.AppDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *kotlinx.coroutines.flow.StateFlow*  
**Navigation component:** *androidx.navigation.compose.NavHost*  
**DI:** *org.koin.core.KoinApplication*  


[MVVM with Compose Navigation Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvvm_compose_implementation)

**Architecture:** *MVVM*    
**Screens:** *androidx.appcompat.app.AppCompatActivity + androidx.fragment.app.Fragment + @Composable*  
**Api:** *retrofit2.Retrofit*  
**Image loading:** *coil.compose.AsyncImage*  
**JsonConverter:** *com.google.gson.Gson*  
**DB:** *androidx.room.RoomDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** a*ndroidx.lifecycle.ViewModel*  
**Observable component:** *androidx.lifecycle.MutableLiveData*  
**Navigation component:** *androidx.navigation.fragment.NavController*  
**DI:** *dagger.hilt.android.HiltAndroidApp*  


[MVVM with Clean Architecture Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvvm_clean_architecture_implementation)

**Architecture:** *MVVM*    
**Screens:** *androidx.appcompat.app.AppCompatActivity + androidx.fragment.app.Fragment*   
**Api:** *retrofit2.Retrofit*  
**Image loading:** *com.bumptech.glide.Glide*   
**JsonConverter:** *com.google.gson.Gson*  
**DB:** *androidx.room.RoomDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *androidx.lifecycle.MutableLiveData*  
**Navigation component:** *androidx.navigation.fragment.NavController*  
**DI:** *dagger.hilt.android.HiltAndroidApp*  


[MVVM Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvvm_implementation)

**Architecture:** *MVVM*    
**Screens:** *androidx.appcompat.app.AppCompatActivity + androidx.fragment.app.Fragment*   
**Api:** *retrofit2.Retrofit*  
**Image loading:** *com.bumptech.glide.Glide JsonConverter: com.google.gson.Gson*  
**DB:** *androidx.room.RoomDatabase*  
**Multithreading:** *kotlinx.coroutines.CoroutineScope*  
**Architectural component:** *androidx.lifecycle.ViewModel*  
**Observable component:** *androidx.lifecycle.MutableLiveData*  
**Navigation component:** *androidx.navigation.fragment.NavController*  
**DI:** *dagger.hilt.android.HiltAndroidApp*  


[MVP Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvp_implementation)

**Architecture:** *MVP    
**Screens:** *androidx.appcompat.app.AppCompatActivity + androidx.fragment.app.Fragment*   
**Api:** *com.android.volley.toolbox.Volley*  
**Image loading:** *com.squareup.picasso.Picasso*   
**JsonConverter:** *com.squareup.moshi.Moshi*  
**DB:** *io.realm.Realm*  
**Multithreading:** *io.reactivex.RxAndroid*  
**Architectural component:** *Presenter*  
**Observable component:** *io.reactivex.Observable*  
**Navigation component:** *androidx.navigation.fragment.NavController*  
**DI:** *dagger.hilt.android.HiltAndroidApp*  


[MVC_with Async Task Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvc_async_task_implementation)

**Architecture:** *MVC*    
**Screens:** *androidx.appcompat.app.AppCompatActivity*   
**Api:** *java.net.HttpURLConnection*  
**Image loading:** *BitmapFactory.decodeStream(HttpURLConnection.inputStream)*   
**JsonConverter:** *com.squareup.moshi.Moshi*  
**DB:** *com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper*  
**Multithreading:** *android.os.AsyncTask*  


[MVC_Native Implementation](https://github.com/TarasovVP/Android-Architecure-Templates/tree/mvc_native_implementation)

**Architecture:** *MVC*    
**Screens:** *androidx.appcompat.app.AppCompatActivity*   
**Api:** *java.net.HttpURLConnection*  
**Image loading:** *BitmapFactory.decodeStream(HttpURLConnection.inputStream)*   
**JsonConverter:** *org.json.JSONObject*  
**DB:** *android.database.sqlite.SQLiteDatabase*  
**Multithreading:** *android.os.Handler + Thread*  
