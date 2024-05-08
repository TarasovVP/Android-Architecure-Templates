import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import org.koin.dsl.module
import presentation.details.DetailsViewModel
import presentation.list.ListViewModel

val iosModule = module {
    single {
        DatabaseDriverFactory()
    }
    factory {
        ListViewModel(get(), get())
    }
    factory {
        DetailsViewModel(get(), get())
    }
}