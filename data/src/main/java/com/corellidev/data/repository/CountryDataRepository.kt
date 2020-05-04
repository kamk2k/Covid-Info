package com.corellidev.data.repository

import com.corellidev.data.datasource.ILocalDataSource
import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.data.util.TimeProvider
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository

private const val SUPPORTED_COUNTRIES_UPDATE_DELAY_IN_MINUTES = 60
private const val COUNTRY_STATISTICS_UPDATE_DELAY_IN_MINUTES = 60 * 3

class CountryDataRepository(
    private val networkDataSource: INetworkDataSource,
    private val localDataSource: ILocalDataSource,
    private val supportedCountriesMapper: Mapper<SupportedCountryResponseModel, CountryEntity>,
    private val statisticsForCountryMapper: Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>,
    private val timeProvider: TimeProvider
) : ICountryDataRepository {

    private var lastSupportedCountriesUpdate: Long = 0L

    override suspend fun getSupportedCountries(): List<CountryEntity> {
        return if (timeProvider.hasXMinutesPassedSince(
                lastSupportedCountriesUpdate,
                timeProvider.getCurrentTime(),
                SUPPORTED_COUNTRIES_UPDATE_DELAY_IN_MINUTES
            )
        ) {
            getSupportedCountriesFromNetworkSource()
        } else {
            val localCountriesList = localDataSource.getCountriesList()
            if (localCountriesList.isEmpty())
                getSupportedCountriesFromNetworkSource()
            else
                localCountriesList
        }
    }

    private suspend fun getSupportedCountriesFromNetworkSource(): List<CountryEntity> {
        lastSupportedCountriesUpdate = timeProvider.getCurrentTime()
        val supportedCountries = supportedCountriesMapper.map(networkDataSource.getSupportedCountriesList())
        localDataSource.storeCountriesData(supportedCountries)
        return supportedCountries
    }

    override suspend fun getStatisticsForCountry(countryEntity: CountryEntity): CountryEntity {
        if (timeProvider.hasXMinutesPassedSince(
                localDataSource.getCountryStatisticsUpdateTimestamp(countryEntity),
                timeProvider.getCurrentTime(),
                COUNTRY_STATISTICS_UPDATE_DELAY_IN_MINUTES
            )
        ) {
            val response = networkDataSource.getCountryStatistics(countryEntity.name)
            response?.let {
                val mappedResponse = statisticsForCountryMapper.map(it)
                localDataSource.storeStatistics(mappedResponse)
                return mappedResponse
            }
            return countryEntity
        } else {
            return localDataSource.getCountryWithStatistics(countryEntity)
        }
    }
}