package com.corellidev.data.datasource

import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.data.retrofit.CovidApi

class RetrofitNetworkDataSource(val api: CovidApi) : INetworkDataSource {

    val countryToSlugMap = mutableMapOf<String?, String?>()

    override suspend fun getSupportedCountriesList(): List<SupportedCountryResponseModel> {
        val result = api.getSupportedCountriesList()
        result.forEach {
            countryToSlugMap.put(it.country, it.slug)
        }
        return result
    }

    override suspend fun getCountryStatistics(country: String): List<CountryDayStatisticsResponseModel>? {
        val slug = countryToSlugMap.get(country)
        if(slug == null) {
            // todo getSupportedCountries and retry
            return emptyList()
        } else {
            return api.getCountryStatistics(slug)
        }
    }

}