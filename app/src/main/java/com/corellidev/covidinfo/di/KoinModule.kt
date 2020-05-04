package com.corellidev.covidinfo.di

import androidx.room.Room
import com.corellidev.covidinfo.list.CountriesListViewModel
import com.corellidev.covidinfo.mapper.CountryEntityToCountriesListItem
import com.corellidev.covidinfo.mapper.CountryEntityToCountryStatistics
import com.corellidev.covidinfo.model.CountriesListItem
import com.corellidev.covidinfo.model.CountryStatistics
import com.corellidev.covidinfo.statistics.CountryStatisticsViewModel
import com.corellidev.data.datasource.ILocalDataSource
import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.datasource.RoomLocalDataSource
import com.corellidev.data.mapper.*
import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.data.repository.CountryDataRepository
import com.corellidev.data.room.AppDatabase
import com.corellidev.data.room.CountryRoomData
import com.corellidev.data.room.DayStatisticsRoomData
import com.corellidev.data.util.TimeProvider
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import com.corellidev.domain.repository.ICountryDataRepository
import com.corellidev.domain.usecase.GetCountryStatisticsUseCase
import com.corellidev.domain.usecase.GetSupportedCountriesUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object Keys {
    const val COUNTRY_ENTITY_TO_COUNTRIES_LIST_ITEM = "COUNTRY_ENTITY_TO_COUNTRIES_LIST_ITEM"
    const val COUNTRY_ENTITY_TO_COUNTRY_STATISTICS = "COUNTRY_ENTITY_TO_COUNTRY_STATISTICS"
    const val COUNTRY_DAY_STATISTICS_TO_DAY_STATISTICS_ENTITY = "COUNTRY_DAY_STATISTICS_TO_DAY_STATISTICS_ENTITY"
    const val COUNTRY_DAY_STATISTICS_LIST_TO_COUNTRY_ENTITY = "COUNTRY_DAY_STATISTICS_LIST_TO_COUNTRY_ENTITY"
    const val SUPPORTED_COUNTRY_TO_COUNTRY_ENTITY = "SUPPORTED_COUNTRY_TO_COUNTRY_ENTITY"
    const val COUNTRY_ENTITY_TO_COUNTRY_ROOM_DATA = "COUNTRY_ENTITY_TO_COUNTRY_ROOM_DATA"
    const val COUNTRY_ENTITY_TO_DAY_STATISTICS_ROOM_DATA_LIST = "COUNTRY_ENTITY_TO_DAY_STATISTICS_ROOM_DATA_LIST"
    const val COUNTRY_ROOM_DATA_TO_COUNTRY_ENTITY = "COUNTRY_ROOM_DATA_TO_COUNTRY_ENTITY"
    const val DAY_STATISTICS_ROOM_DATA_TO_DAY_STATISTICS_ENTITY = "DAY_STATISTICS_ROOM_DATA_TO_DAY_STATISTICS_ENTITY"
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
    single<Mapper<CountryEntity, CountryRoomData>>(named(Keys.COUNTRY_ENTITY_TO_COUNTRY_ROOM_DATA)) { CountryEntityToCountryRoomData() }
    single<Mapper<CountryEntity, List<DayStatisticsRoomData>>>(named(Keys.COUNTRY_ENTITY_TO_DAY_STATISTICS_ROOM_DATA_LIST)) { CountryEntityToDayStatisticsRoomDataList() }
    single<Mapper<CountryRoomData, CountryEntity>>(named(Keys.COUNTRY_ROOM_DATA_TO_COUNTRY_ENTITY)) { CountryRoomDataToCountryEntity() }
    single<Mapper<DayStatisticsRoomData, DayStatisticsEntity>>(named(Keys.DAY_STATISTICS_ROOM_DATA_TO_DAY_STATISTICS_ENTITY)) { DayStatisticsRoomDataToDayStatisticsEntity() }
    single<INetworkDataSource> { MockNetworkDataSource() }
    single<ILocalDataSource> {
        RoomLocalDataSource(
            get(),
            get(),
            get(named(Keys.COUNTRY_ENTITY_TO_COUNTRY_ROOM_DATA)),
            get(named(Keys.COUNTRY_ENTITY_TO_DAY_STATISTICS_ROOM_DATA_LIST)),
            get(named(Keys.COUNTRY_ROOM_DATA_TO_COUNTRY_ENTITY)),
            get(named(Keys.DAY_STATISTICS_ROOM_DATA_TO_DAY_STATISTICS_ENTITY))
        )
    }
    single { TimeProvider() }
    single<ICountryDataRepository> {
        CountryDataRepository(
            get(),
            get(),
            get(named(Keys.SUPPORTED_COUNTRY_TO_COUNTRY_ENTITY)),
            get(named(Keys.COUNTRY_DAY_STATISTICS_LIST_TO_COUNTRY_ENTITY)),
            get()
        )
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, AppDatabase.DB_NAME
        )
            .build()
    }
    single { get<AppDatabase>().getCountryRoomDAO() }
    single { get<AppDatabase>().getDayStatisticsRoomDAO() }
}

val domainModule = module {
    factory { GetSupportedCountriesUseCase(get()) }
    factory { GetCountryStatisticsUseCase(get()) }
}