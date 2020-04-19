package com.corellidev.covidinfo.mapper

import com.corellidev.covidinfo.model.CountriesListItem
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity

class CountryEntityToCountriesListItem : Mapper<CountryEntity, CountriesListItem>() {
    override fun convert(from: CountryEntity): CountriesListItem = CountriesListItem(from.name)
}