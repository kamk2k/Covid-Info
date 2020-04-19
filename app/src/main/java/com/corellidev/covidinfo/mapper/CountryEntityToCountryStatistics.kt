package com.corellidev.covidinfo.mapper

import com.corellidev.covidinfo.model.CountryStatistics
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity

class CountryEntityToCountryStatistics : Mapper<CountryEntity, CountryStatistics>() {
    override fun convert(from: CountryEntity): CountryStatistics {
        return if(from.statistics.isEmpty()) {
            CountryStatistics(countryName = from.name, noStats =  true)
        } else {
            val lastDayStats = from.statistics.maxBy { it.date }!!
            CountryStatistics(
                from.name,
                lastDayStats.date,
                lastDayStats.confirmed,
                lastDayStats.recovered,
                lastDayStats.deaths)
        }
    }
}