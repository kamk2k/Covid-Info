package com.corellidev.domain.usecase

import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository

class GetCountryStatisticsUseCase(private val repository: ICountryDataRepository) : UseCase<CountryEntity, CountryEntity> {
    override suspend fun execute(data: CountryEntity?): CountryEntity {
        data?.let {
            return repository.getStatisticsForCountry(it)
        } ?: throw IllegalArgumentException("No country provided")
    }
}