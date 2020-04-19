package com.corellidev.data.repository

import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository

class CountryDataRepository(
    private val networkDataSource: INetworkDataSource,
    private val supportedCountriesMapper: Mapper<SupportedCountryResponseModel, CountryEntity>,
    private val statisticsForCountryMapper: Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>
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