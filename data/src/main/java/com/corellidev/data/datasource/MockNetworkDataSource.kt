package com.corellidev.data.datasource

import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import kotlinx.coroutines.delay

private const val SIMULATED_DELAY_LENGTH = 200L

class MockNetworkDataSource : INetworkDataSource {

    val mockSupportedCountries = listOf(
        SupportedCountryResponseModel("Poland", "poland", "PL"),
        SupportedCountryResponseModel("Italy", "italy", "IT"),
        SupportedCountryResponseModel("China", "china", "CN"),
        SupportedCountryResponseModel("Spain", "spain", "ES")
    )

    val mockDayStatics = mapOf<String, List<CountryDayStatisticsResponseModel>>(
        Pair(
            "poland", listOf(
                CountryDayStatisticsResponseModel(
                    country = "Poland",
                    confirmed = 6674,
                    deaths = 232,
                    recovered = 439,
                    date = "2020-04-12T00:00:00Z"
                ),
                CountryDayStatisticsResponseModel(
                    country = "Poland",
                    confirmed = 6934,
                    deaths = 245,
                    recovered = 487,
                    date = "2020-04-13T00:00:00Z"
                ),
                CountryDayStatisticsResponseModel(
                    country = "Poland",
                    confirmed = 7202,
                    deaths = 263,
                    recovered = 618,
                    date = "2020-04-14T00:00:00Z"
                )
            )
        ),
        Pair(
            "italy", listOf(
                CountryDayStatisticsResponseModel(
                    country = "Poland",
                    confirmed = 16674,
                    deaths = 1232,
                    recovered = 1439,
                    date = "2020-04-12T00:00:00Z"
                ),
                CountryDayStatisticsResponseModel(
                    country = "Poland",
                    confirmed = 16934,
                    deaths = 1245,
                    recovered = 1487,
                    date = "2020-04-13T00:00:00Z"
                ),
                CountryDayStatisticsResponseModel(
                    country = "Poland",
                    confirmed = 17202,
                    deaths = 1263,
                    recovered = 1618,
                    date = "2020-04-14T00:00:00Z"
                )
            )
        )
    )

    override suspend fun getSupportedCountriesList(): List<SupportedCountryResponseModel> {
        delay(SIMULATED_DELAY_LENGTH)
        return mockSupportedCountries
        }

    override suspend fun getCountryStatistics(country: String): List<CountryDayStatisticsResponseModel>? {
        delay(SIMULATED_DELAY_LENGTH)
        return mockDayStatics.get(country)
    }

}