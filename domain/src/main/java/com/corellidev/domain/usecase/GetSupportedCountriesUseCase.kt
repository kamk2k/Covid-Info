package com.corellidev.domain.usecase

import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository

class GetSupportedCountriesUseCase(private val repository: ICountryDataRepository) : UseCase<Any, List<CountryEntity>> {
    override suspend fun execute(data: Any?): List<CountryEntity> {
        return repository.getSupportedCountries()
    }
}