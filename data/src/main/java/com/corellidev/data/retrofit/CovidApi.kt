package com.corellidev.data.retrofit

import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {

    @GET("countries")
    suspend fun getSupportedCountriesList(): List<SupportedCountryResponseModel>

    @GET("country/{slug}")
    suspend fun getCountryStatistics(@Path("slug") slug: String): List<CountryDayStatisticsResponseModel>

}