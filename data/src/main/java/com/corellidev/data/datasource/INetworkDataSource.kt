package com.corellidev.data.datasource

import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel

interface INetworkDataSource {
    suspend fun getSupportedCountriesList(): List<SupportedCountryResponseModel>
    suspend fun getCountryStatistics(country: String): List<CountryDayStatisticsResponseModel>?
}
