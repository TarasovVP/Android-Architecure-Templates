import com.vnteam.architecturetemplates.di.appModule
import com.vnteam.architecturetemplates.iosModule
import org.koin.core.context.startKoin

fun doInitKoin() = startKoin {
    modules(appModule, iosModule)
}