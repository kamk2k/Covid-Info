package com.corellidev.data.mapper

import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity

class SupportedCountryResponseModelToCountryEntity:
    Mapper<SupportedCountryResponseModel, CountryEntity>() {

    override fun convert(from: SupportedCountryResponseModel): CountryEntity {
        return CountryEntity(from.country ?: "")
    }
}