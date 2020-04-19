package com.corellidev.covidinfo.mapper

import com.corellidev.covidinfo.model.CountryStatistics
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import org.assertj.core.api.Assertions
import org.joda.time.DateTime
import org.junit.Test

class CountryEntityToCountryStatisticsTest {

    private val testData = CountryEntity(
        "Poland",
        listOf(
            DayStatisticsEntity(DateTime("2020-04-12T00:00:00Z").toDate(), 6674, 439, 232),
            DayStatisticsEntity(DateTime("2020-04-13T00:00:00Z").toDate(), 6934, 487, 245),
            DayStatisticsEntity(DateTime("2020-04-14T00:00:00Z").toDate(), 7202, 618, 263)
        )
    )
    private val expectedResult =
        CountryStatistics("Poland", DateTime("2020-04-14T00:00:00Z").toDate(), 7202, 618, 263)

    private val testData_noStats = CountryEntity(
        "Poland",
        emptyList()
    )
    private val expectedResult_noStats =
        CountryStatistics("Poland", null, 0, 0, 0, true)

    @Test
    fun convert() {
        Assertions.assertThat(CountryEntityToCountryStatistics().convert(testData))
            .isEqualTo(expectedResult)
    }

    @Test
    fun convert_noStats() {
        Assertions.assertThat(CountryEntityToCountryStatistics().convert(testData_noStats))
            .isEqualTo(expectedResult_noStats)
    }
}