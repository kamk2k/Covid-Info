package com.corellidev.covidinfo.model

import java.util.*

data class CountryStatistics(
    val countryName: String,
    val date: Date? = null,
    val confirmed: Int = 0,
    val recovered: Int = 0,
    val deaths: Int = 0,
    val noStats: Boolean = false
)