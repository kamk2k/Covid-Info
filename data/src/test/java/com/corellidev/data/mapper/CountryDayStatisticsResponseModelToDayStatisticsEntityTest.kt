package com.corellidev.data.mapper

import com.corellidev.data.countryDayStatisticsResponseModel
import com.corellidev.data.dayStatisticsEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountryDayStatisticsResponseModelToDayStatisticsEntityTest {
    @Test
    fun convert() {
        assertThat(CountryDayStatisticsResponseModelToDayStatisticsEntity().convert(countryDayStatisticsResponseModel))
            .isEqualTo(dayStatisticsEntity)
    }
}