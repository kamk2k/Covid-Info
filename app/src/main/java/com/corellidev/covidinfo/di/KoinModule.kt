package com.corellidev.covidinfo.di

import com.corellidev.covidinfo.list.CountriesListViewModel
import com.corellidev.covidinfo.mapper.CountryEntityToCountriesListItem
import com.corellidev.covidinfo.mapper.CountryEntityToCountryStatistics
import com.corellidev.covidinfo.model.CountriesListItem
import com.corellidev.covidinfo.model.CountryStatistics
import com.corellidev.covidinfo.statistics.CountryStatisticsViewModel
import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToCountryEntity
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToDayStatisticsEntity
import com.corellidev.data.mapper.SupportedCountryResponseModelToCountryEntity
import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.data.repository.CountryDataRepository
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import com.corellidev.domain.repository.ICountryDataRepository
import com.corellidev.domain.usecase.GetCountryStatisticsUseCase
import com.corellidev.domain.usecase.GetSupportedCountriesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object Keys {
    const val COUNTRY_ENTITY_TO_COUNTRIES_LIST_ITEM = "COUNTRY_ENTITY_TO_COUNTRIES_LIST_ITEM"
    const val COUNTRY_ENTITY_TO_COUNTRY_STATISTICS = "COUNTRY_ENTITY_TO_COUNTRY_STATISTICS"
    const val COUNTRY_DAY_STATISTICS_TO_DAY_STATISTICS_ENTITY = "COUNTRY_DAY_STATISTICS_TO_DAY_STATISTICS_ENTITY"
    const val COUNTRY_DAY_STATISTICS_LIST_TO_COUNTRY_ENTITY = "COUNTRY_DAY_STATISTICS_LIST_TO_COUNTRY_ENTITY"
    const val SUPPORTED_COUNTRY_TO_COUNTRY_ENTITY = "SUPPORTED_COUNTRY_TO_COUNTRY_ENTITY"
}
val viewModule = module {
    single<Mapper<CountryEntity, CountriesListItem>>(named(Keys.COUNTRY_ENTITY_TO_COUNTRIES_LIST_ITEM)) { CountryEntityToCountriesListItem() }
    single<Mapper<CountryEntity, CountryStatistics>>(named(Keys.COUNTRY_ENTITY_TO_COUNTRY_STATISTICS)) { CountryEntityToCountryStatistics() }
    viewModel { CountryStatisticsViewModel(get(), get(named(Keys.COUNTRY_ENTITY_TO_COUNTRY_STATISTICS))) }
    viewModel { CountriesListViewModel(get(), get(named(Keys.COUNTRY_ENTITY_TO_COUNTRIES_LIST_ITEM))) }
}

val dataModule = module {
    single<Mapper<CountryDayStatisticsResponseModel, DayStatisticsEntity>>(named(Keys.COUNTRY_DAY_STATISTICS_TO_DAY_STATISTICS_ENTITY)) { CountryDayStatisticsResponseModelToDayStatisticsEntity() }
    single<Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>>(named(Keys.COUNTRY_DAY_STATISTICS_LIST_TO_COUNTRY_ENTITY)) {
        CountryDayStatisticsResponseModelToCountryEntity(
            get(named(Keys.COUNTRY_DAY_STATISTICS_TO_DAY_STATISTICS_ENTITY))
        )
    }
    single<Mapper<SupportedCountryResponseModel, CountryEntity>>(named(Keys.SUPPORTED_COUNTRY_TO_COUNTRY_ENTITY)) { SupportedCountryResponseModelToCountryEntity() }
    single<INetworkDataSource> { MockNetworkDataSource() }
    single<ICountryDataRepository> {
        CountryDataRepository(
            get(),
            get(named(Keys.SUPPORTED_COUNTRY_TO_COUNTRY_ENTITY)),
            get(named(Keys.COUNTRY_DAY_STATISTICS_LIST_TO_COUNTRY_ENTITY))
        )
    }
}

val domainModule = module {
    factory { GetSupportedCountriesUseCase(get()) }
    factory { GetCountryStatisticsUseCase(get()) }
}