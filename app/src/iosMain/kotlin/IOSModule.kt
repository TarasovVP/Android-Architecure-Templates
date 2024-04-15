
import com.vnteam.architecturetemplates.PlatformMessageDisplayer
import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import details.DetailsViewModel
import list.ListViewModel
import org.koin.dsl.module

val iosModule = module {
    single {
        DatabaseDriverFactory()
    }
    single {
        PlatformMessageDisplayer()
    }
    single {
        ListViewModel(get(), get())
    }
    single {
        DetailsViewModel(get(), get())
    }
}