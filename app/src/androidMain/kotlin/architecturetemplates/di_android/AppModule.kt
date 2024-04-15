package architecturetemplates.di_android

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import presentation.mappers.OwnerUIMapper
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import architecturetemplates.presentation.details.DetailsViewModel
import architecturetemplates.presentation.list.ListViewModel
import com.vnteam.architecturetemplates.PlatformMessageDisplayer
import com.vnteam.architecturetemplates.presentation.mapperimpls.ForkUIMapperImpl
import com.vnteam.architecturetemplates.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.architecturetemplates.presentation.usecaseimpl.ForkUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val androidModule = module {

    single {
        DatabaseDriverFactory(androidContext())
    }

    single {
        PlatformMessageDisplayer(androidContext())
    }

    viewModel {
        ListViewModel(get(), get())
    }
    viewModel {
        DetailsViewModel(get(), get())
    }
}