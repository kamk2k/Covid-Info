package com.corellidev.data.repository

import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository

class CountryDataRepository : ICountryDataRepository {
    override suspend fun getSupportedCountries(): List<CountryEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getStatisticsForCountry(countryEntity: CountryEntity): CountryEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}