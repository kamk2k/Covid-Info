package com.corellidev.data.model

import com.squareup.moshi.Json

data class CountryDayStatisticsResponseModel(
    @Json(name = "Country")
    val country: String? = null,
    @Json(name = "CountryCode")
    val countryCode: String? = null,
    @Json(name = "Province")
    val province: String? = null,
    @Json(name = "City")
    val city: String? = null,
    @Json(name = "CityCode")
    val cityCode: String? = null,
    @Json(name = "Lat")
    val lat: String? = null,
    @Json(name = "Lon")
    val lon: String? = null,
    @Json(name = "Confirmed")
    val confirmed: Int? = null,
    @Json(name = "Deaths")
    val deaths: Int? = null,
    @Json(name = "Recovered")
    val recovered: Int? = null,
    @Json(name = "Active")
    val active: Int? = null,
    @Json(name = "Date")
    val date: String? = null
    )