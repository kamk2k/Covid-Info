package com.corellidev.covidinfo.mapper

import com.corellidev.covidinfo.model.CountriesListItem
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import org.assertj.core.api.Assertions
import org.joda.time.DateTime
import org.junit.Test

class CountryEntityToCountriesListItemTest {

    val testData = CountryEntity(
        "Poland",
        listOf(DayStatisticsEntity(DateTime("2020-04-12T00:00:00Z").toDate(), 6674, 439, 232))
    )

    val expectedResult = CountriesListItem("Poland")

    @Test
    fun convert() {
        Assertions.assertThat(CountryEntityToCountriesListItem().convert(testData))
            .isEqualTo(expectedResult)
    }
}