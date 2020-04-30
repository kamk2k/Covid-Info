package com.corellidev.data.mapper

import com.corellidev.data.countryDayStatisticsResponseModel
import com.corellidev.data.countryEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountryDayStatisticsResponseModelToCountryEntityTest {
    @Test
    fun convert() {
        assertThat(
            CountryDayStatisticsResponseModelToCountryEntity(
                CountryDayStatisticsResponseModelToDayStatisticsEntity()
            ).convert(listOf(countryDayStatisticsResponseModel))
        )
            .isEqualTo(countryEntity)
    }
}