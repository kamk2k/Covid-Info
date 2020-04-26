package com.corellidev.data.datasource

import com.corellidev.domain.entity.CountryEntity

interface ILocalDataSource {
    suspend fun storeCountriesData(countriesData: List<CountryEntity>)
    suspend fun storeStatistics(countryDataWithStats: CountryEntity)
    suspend fun getCountriesList(): List<CountryEntity>
    suspend fun getCountryWithStatistics(country: CountryEntity): CountryEntity
    suspend fun getCountryStatisticsUpdateTimestamp(country: CountryEntity): Long
}