package architecturetemplates.di_android

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import architecturetemplates.presentation.mappers.ForkUIMapper
import architecturetemplates.presentation.mappers.OwnerUIMapper
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import architecturetemplates.presentation.details.DetailsViewModel
import architecturetemplates.presentation.list.ListViewModel
import architecturetemplates.presentation.mapperimpls.ForkUIMapperImpl
import architecturetemplates.presentation.mapperimpls.OwnerUIMapperImpl
import architecturetemplates.presentation.usecaseimpl.ForkUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val androidModule = module {

    single {
        DatabaseDriverFactory(androidContext())
    }

    single<OwnerUIMapper> { OwnerUIMapperImpl() }

    single<ForkUIMapper> { ForkUIMapperImpl(get()) }

    single<ForkUseCase> { ForkUseCaseImpl(get(), get()) }

    viewModel {
        ListViewModel(get(), get())
    }
    viewModel {
        DetailsViewModel(get(), get())
    }
}