package com.corellidev.covidinfo.di

import com.corellidev.data.repository.CountryDataRepository
import com.corellidev.domain.repository.ICountryDataRepository
import com.corellidev.domain.usecase.GetCountryStatisticsUseCase
import com.corellidev.domain.usecase.GetSupportedCountriesUseCase
import org.koin.dsl.module

val viewModule = module {

}

val dataModule = module {
    single<ICountryDataRepository> { CountryDataRepository() }
}

val domainModule = module {
    factory { GetSupportedCountriesUseCase(get()) }
    factory { GetCountryStatisticsUseCase(get()) }
}