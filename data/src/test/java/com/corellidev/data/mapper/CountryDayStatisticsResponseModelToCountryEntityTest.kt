package com.corellidev.data.mapper

import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Test

class CountryDayStatisticsResponseModelToCountryEntityTest {

    val testData = listOf(
        CountryDayStatisticsResponseModel(
            country = "Poland",
            confirmed = 6674,
            deaths = 232,
            recovered = 439,
            date = "2020-04-12T00:00:00Z"
        )
    )

    val expectedResult = CountryEntity(
        "Poland",
        listOf(DayStatisticsEntity(DateTime("2020-04-12T00:00:00Z").toDate(), 6674, 439, 232))
    )

    @Test
    fun convert() {
        assertThat(
            CountryDayStatisticsResponseModelToCountryEntity(
                CountryDayStatisticsResponseModelToDayStatisticsEntity()
            ).convert(testData)
        )
            .isEqualTo(expectedResult)
    }
}