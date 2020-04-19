package com.corellidev.covidinfo.di

import com.corellidev.covidinfo.list.CountriesListViewModel
import com.corellidev.covidinfo.mapper.CountryEntityToCountriesListItem
import com.corellidev.covidinfo.mapper.CountryEntityToCountryStatistics
import com.corellidev.covidinfo.statistics.CountryStatisticsViewModel
import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToCountryEntity
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToDayStatisticsEntity
import com.corellidev.data.mapper.SupportedCountryResponseModelToCountryEntity
import com.corellidev.data.repository.CountryDataRepository
import com.corellidev.domain.repository.ICountryDataRepository
import com.corellidev.domain.usecase.GetCountryStatisticsUseCase
import com.corellidev.domain.usecase.GetSupportedCountriesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    single { CountryEntityToCountriesListItem() }
    single { CountryEntityToCountryStatistics() }
    viewModel { CountryStatisticsViewModel(get(), get()) }
    viewModel { CountriesListViewModel(get(), get()) }
}

val dataModule = module {
    single { CountryDayStatisticsResponseModelToDayStatisticsEntity() }
    single { CountryDayStatisticsResponseModelToCountryEntity(get()) }
    single { SupportedCountryResponseModelToCountryEntity() }
    single<INetworkDataSource> { MockNetworkDataSource() }
    single<ICountryDataRepository> { CountryDataRepository(get(), get(), get()) }
}

val domainModule = module {
    factory { GetSupportedCountriesUseCase(get()) }
    factory { GetCountryStatisticsUseCase(get()) }
}