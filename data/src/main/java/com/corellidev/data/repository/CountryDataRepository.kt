package com.corellidev.data.repository

import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToCountryEntity
import com.corellidev.data.mapper.SupportedCountryResponseModelToCountryEntity
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository

class CountryDataRepository(
    val networkDataSource: INetworkDataSource,
    val supportedCountriesMapper: SupportedCountryResponseModelToCountryEntity,
    val statisticsForCountryMapper: CountryDayStatisticsResponseModelToCountryEntity
) : ICountryDataRepository {
    override suspend fun getSupportedCountries(): List<CountryEntity> {
        return supportedCountriesMapper.map(networkDataSource.getSupportedCountriesList())
    }

    override suspend fun getStatisticsForCountry(countryEntity: CountryEntity): CountryEntity {
        //TODO country to slug mapping
        val response = networkDataSource.getCountryStatistics(countryEntity.name)
        return if (response != null) statisticsForCountryMapper.map(response) else countryEntity
    }
}