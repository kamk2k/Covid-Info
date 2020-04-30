package com.corellidev.data.datasource

import com.corellidev.domain.entity.CountryEntity

class MockLocalDataSource : ILocalDataSource {

    val FAKE_STATISTICS_UPDATE_TIMESTAMP = 1588259683L
    var storedCountriesData = mutableMapOf<String, CountryEntity>()

    override suspend fun storeCountriesData(countriesData: List<CountryEntity>) {
        storedCountriesData.putAll(countriesData.map { it.name to it }.toMap())
    }

    override suspend fun storeStatistics(countryDataWithStats: CountryEntity) {
        storedCountriesData.put(countryDataWithStats.name, countryDataWithStats)
    }

    override suspend fun getCountriesList(): List<CountryEntity> =
        storedCountriesData.map { CountryEntity(it.value.name) }

    override suspend fun getCountryWithStatistics(country: CountryEntity): CountryEntity =
        storedCountriesData[country.name]!!

    override suspend fun getCountryStatisticsUpdateTimestamp(country: CountryEntity): Long =
        FAKE_STATISTICS_UPDATE_TIMESTAMP
}