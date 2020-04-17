package com.corellidev.covidinfo.di

import com.corellidev.covidinfo.list.CountriesListViewModel
import com.corellidev.covidinfo.statistics.CountryStatisticsViewModel
import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.repository.CountryDataRepository
import com.corellidev.domain.repository.ICountryDataRepository
import com.corellidev.domain.usecase.GetCountryStatisticsUseCase
import com.corellidev.domain.usecase.GetSupportedCountriesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel { CountryStatisticsViewModel() }
    viewModel { CountriesListViewModel() }
}

val dataModule = module {
    single<INetworkDataSource> { MockNetworkDataSource() }
    single<ICountryDataRepository> { CountryDataRepository(get()) }
}

val domainModule = module {
    factory { GetSupportedCountriesUseCase(get()) }
    factory { GetCountryStatisticsUseCase(get()) }
}