package com.corellidev.data.repository

import com.corellidev.data.datasource.ILocalDataSource
import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository
import org.joda.time.DateTime
import org.joda.time.Duration

private const val SUPPORTED_COUNTRIES_UPDATE_DELAY_IN_MINUTES = 60
private const val COUNTRY_STATISTICS_UPDATE_DELAY_IN_MINUTES = 60 * 3

class CountryDataRepository(
    private val networkDataSource: INetworkDataSource,
    private val localDataSource: ILocalDataSource,
    private val supportedCountriesMapper: Mapper<SupportedCountryResponseModel, CountryEntity>,
    private val statisticsForCountryMapper: Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>
) : ICountryDataRepository {

    private var lastSupportedCountriesUpdate: DateTime = DateTime(0L)

    override suspend fun getSupportedCountries(): List<CountryEntity> {
        return if (hasXMinutesPassedSince(
                lastSupportedCountriesUpdate,
                DateTime(),
                SUPPORTED_COUNTRIES_UPDATE_DELAY_IN_MINUTES
            )
        ) {
            val localCountriesList = localDataSource.getCountriesList()
            if (localCountriesList.isEmpty())
                getSupportedCountriesFromNetworkSource()
            else
                localCountriesList
        } else {
            getSupportedCountriesFromNetworkSource()
        }
    }

    private suspend fun getSupportedCountriesFromNetworkSource(): List<CountryEntity> {
        lastSupportedCountriesUpdate = DateTime()
        val supportedCountries = supportedCountriesMapper.map(networkDataSource.getSupportedCountriesList())
        localDataSource.storeCountriesData(supportedCountries)
        return supportedCountries
    }

    override suspend fun getStatisticsForCountry(countryEntity: CountryEntity): CountryEntity {
        if (hasXMinutesPassedSince(
                DateTime(localDataSource.getCountryStatisticsUpdateTimestamp(countryEntity)),
                DateTime(),
                COUNTRY_STATISTICS_UPDATE_DELAY_IN_MINUTES
            )
        ) {
            return localDataSource.getCountryWithStatistics(countryEntity)
        } else {
            val response = networkDataSource.getCountryStatistics(countryEntity.name)
            response?.let {
                val mappedResponse = statisticsForCountryMapper.map(it)
                localDataSource.storeStatistics(mappedResponse)
                return mappedResponse
            }
            return countryEntity
        }
    }

    private fun hasXMinutesPassedSince(start: DateTime, end: DateTime, minutes: Int): Boolean =
        Duration(start, end).standardMinutes >= minutes
}