package com.corellidev.domain.repository

import com.corellidev.domain.entity.CountryEntity

interface ICountryDataRepository {
    suspend fun getSupportedCountries() : List<CountryEntity>
    suspend fun getStatisticsForCountry(countryEntity: CountryEntity) : CountryEntity
}