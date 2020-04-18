package com.corellidev.data.repository

import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToCountryEntity
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToDayStatisticsEntity
import com.corellidev.data.mapper.SupportedCountryResponseModelToCountryEntity
import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import com.corellidev.domain.repository.ICountryDataRepository
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class CountryDataRepositoryTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(module {
            single { CountryDayStatisticsResponseModelToDayStatisticsEntity() }
            single { CountryDayStatisticsResponseModelToCountryEntity(get()) }
            single { SupportedCountryResponseModelToCountryEntity() }
            single<INetworkDataSource> { MockNetworkDataSource() }
            single<ICountryDataRepository> { CountryDataRepository(get(), get(), get()) }
        })
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val testRepository by inject<ICountryDataRepository>()

    private val statisticsForCountryTestData = CountryEntity("poland")
    private val expectedStatisticsForCountryResult = CountryEntity(
        "Poland",
        listOf(
            DayStatisticsEntity(DateTime("2020-04-12T00:00:00Z").toDate(), 6674, 439, 232),
            DayStatisticsEntity(DateTime("2020-04-13T00:00:00Z").toDate(), 6934, 487, 245),
            DayStatisticsEntity(DateTime("2020-04-14T00:00:00Z").toDate(), 7202, 618, 263)
        )
    )
    private val expectedSupportedCountriesResult = listOf(
        CountryEntity("Poland"),
        CountryEntity("Italy"),
        CountryEntity("China"),
        CountryEntity("Spain")
    )
    private val mockedSupportedCountryResponse = listOf(
        SupportedCountryResponseModel("Poland"),
        SupportedCountryResponseModel("Italy"),
        SupportedCountryResponseModel("China"),
        SupportedCountryResponseModel("Spain")
    )
    private val mockedCountryStatisticsResponse = listOf(
        CountryDayStatisticsResponseModel("Poland"),
        CountryDayStatisticsResponseModel("Poland"),
        CountryDayStatisticsResponseModel("Poland"),
        CountryDayStatisticsResponseModel("Poland")
    )

    @Test
    fun getSupportedCountries() {
        runBlockingTest {
            assertThat(testRepository.getSupportedCountries())
                .isEqualTo(expectedSupportedCountriesResult)
        }
    }

    @Test
    fun getSupportedCountries_mockedDependencies() {
        runBlockingTest {
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getSupportedCountriesList())
                    .willReturn(mockedSupportedCountryResponse)
            }
            val mapper = declareMock<SupportedCountryResponseModelToCountryEntity>()
            val repository = get<ICountryDataRepository>()

            repository.getSupportedCountries()

            verify(networkDataSource, times(1))
                .getSupportedCountriesList()
            verify(mapper, times(1))
                .map(mockedSupportedCountryResponse)
        }
    }

    @Test
    fun getStatisticsForCountry() {
        runBlockingTest {
            assertThat(testRepository.getStatisticsForCountry(statisticsForCountryTestData))
                .isEqualTo(expectedStatisticsForCountryResult)
        }
    }

    @Test
    fun getStatisticsForCountry_mockedDependencies() {
        runBlockingTest {
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getCountryStatistics(statisticsForCountryTestData.name))
                    .willReturn(mockedCountryStatisticsResponse)
            }
            val mapper = declareMock<CountryDayStatisticsResponseModelToCountryEntity>()
            val repository = get<ICountryDataRepository>()

            repository.getStatisticsForCountry(statisticsForCountryTestData)

            verify(networkDataSource, times(1))
                .getCountryStatistics(statisticsForCountryTestData.name)
            verify(mapper, times(1))
                .map(mockedCountryStatisticsResponse)
        }
    }
}