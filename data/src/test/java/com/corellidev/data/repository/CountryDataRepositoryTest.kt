package com.corellidev.data.repository

import com.corellidev.data.countryDayStatisticsResponseModel
import com.corellidev.data.countryEntity
import com.corellidev.data.countryEntity_nameOnly
import com.corellidev.data.datasource.ILocalDataSource
import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockLocalDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToCountryEntity
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToDayStatisticsEntity
import com.corellidev.data.mapper.SupportedCountryResponseModelToCountryEntity
import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.data.supportedCountryResponseModel
import com.corellidev.data.util.TimeProvider
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import com.corellidev.domain.repository.ICountryDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class CountryDataRepositoryTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(module {
            single { TimeProvider() }
            single<Mapper<CountryDayStatisticsResponseModel, DayStatisticsEntity>>(named("CountryDayStatisticsResponseModelToDayStatisticsEntity")) { CountryDayStatisticsResponseModelToDayStatisticsEntity() }
            single<Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>>(named("CountryDayStatisticsResponseModelToCountryEntity")) {
                CountryDayStatisticsResponseModelToCountryEntity(
                    get(named("CountryDayStatisticsResponseModelToDayStatisticsEntity"))
                )
            }
            single<Mapper<SupportedCountryResponseModel, CountryEntity>>(named("SupportedCountryResponseModelToCountryEntity")) { SupportedCountryResponseModelToCountryEntity() }
            single<INetworkDataSource> { MockNetworkDataSource() }
            single<ILocalDataSource> { MockLocalDataSource() }
            single<ICountryDataRepository> {
                CountryDataRepository(
                    get(),
                    get(),
                    get(named("SupportedCountryResponseModelToCountryEntity")),
                    get(named("CountryDayStatisticsResponseModelToCountryEntity")),
                    get()
                )
            }
        })
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val testRepository by inject<ICountryDataRepository>()

    @Test
    fun getSupportedCountries_60minsAfterUpdate() {
        runBlockingTest {
            declareMock<TimeProvider> {
                given(getCurrentTime()).willReturn(0L)
                given(
                    hasXMinutesPassedSince(
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyInt()
                    )
                ).willReturn(true)
            }
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getSupportedCountriesList())
                    .willReturn(listOf(supportedCountryResponseModel))
            }
            val mapper =
                declareMock<Mapper<SupportedCountryResponseModel, CountryEntity>>(
                    named("SupportedCountryResponseModelToCountryEntity")
                ) {
                    given(map(listOf(supportedCountryResponseModel)))
                        .willReturn(listOf(countryEntity_nameOnly))
                }
            val localDataSource = declareMock<ILocalDataSource>()

            assertThat(testRepository.getSupportedCountries())
                .isEqualTo(listOf(countryEntity_nameOnly))

            verify(networkDataSource, times(1))
                .getSupportedCountriesList()
            verify(mapper, times(1))
                .map(listOf(supportedCountryResponseModel))
            verify(localDataSource, times(1))
                .storeCountriesData(listOf(countryEntity_nameOnly))
        }
    }

    @Test
    fun getSupportedCountries_lessThan60minsAfterUpdateLocalSourceEmpty() {
        runBlockingTest {
            declareMock<TimeProvider> {
                given(getCurrentTime()).willReturn(0L)
                given(
                    hasXMinutesPassedSince(
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyInt()
                    )
                ).willReturn(false)
            }
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getSupportedCountriesList())
                    .willReturn(listOf(supportedCountryResponseModel))
            }
            val mapper =
                declareMock<Mapper<SupportedCountryResponseModel, CountryEntity>>(
                    named("SupportedCountryResponseModelToCountryEntity")
                ) {
                    given(map(listOf(supportedCountryResponseModel)))
                        .willReturn(listOf(countryEntity_nameOnly))
                }
            val localDataSource = declareMock<ILocalDataSource> {
                given(getCountriesList()).willReturn(emptyList())
            }

            assertThat(testRepository.getSupportedCountries())
                .isEqualTo(listOf(countryEntity_nameOnly))

            verify(networkDataSource, times(1))
                .getSupportedCountriesList()
            verify(mapper, times(1))
                .map(listOf(supportedCountryResponseModel))
            verify(localDataSource, times(1))
                .storeCountriesData(listOf(countryEntity_nameOnly))
            verify(localDataSource, times(1))
                .getCountriesList()
        }
    }

    @Test
    fun getSupportedCountries_lessThan60minsAfterUpdateLocalSourceNotEmpty() {
        runBlockingTest {
            declareMock<TimeProvider> {
                given(getCurrentTime()).willReturn(0L)
                given(
                    hasXMinutesPassedSince(
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyInt()
                    )
                ).willReturn(false)
            }
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getSupportedCountriesList())
                    .willReturn(listOf(supportedCountryResponseModel))
            }
            val mapper =
                declareMock<Mapper<SupportedCountryResponseModel, CountryEntity>>(
                    named("SupportedCountryResponseModelToCountryEntity")
                ) {
                    given(map(listOf(supportedCountryResponseModel)))
                        .willReturn(listOf(countryEntity_nameOnly))
                }
            val localDataSource = declareMock<ILocalDataSource> {
                given(getCountriesList()).willReturn(listOf(countryEntity_nameOnly))
            }

            assertThat(testRepository.getSupportedCountries())
                .isEqualTo(listOf(countryEntity_nameOnly))

            verify(networkDataSource, times(0))
                .getSupportedCountriesList()
            verify(mapper, times(0))
                .map(listOf(supportedCountryResponseModel))
            verify(localDataSource, times(0))
                .storeCountriesData(listOf(countryEntity_nameOnly))
            verify(localDataSource, times(1))
                .getCountriesList()
        }
    }

    @Test
    fun getStatisticsForCountry_3hoursAfterUpdateAndResponseNotNull() {
        runBlockingTest {
            declareMock<TimeProvider> {
                given(getCurrentTime()).willReturn(0L)
                given(
                    hasXMinutesPassedSince(
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyInt()
                    )
                ).willReturn(true)
            }
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getCountryStatistics(countryEntity_nameOnly.name))
                    .willReturn(listOf(countryDayStatisticsResponseModel))
            }
            val mapper =
                declareMock<Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>>(
                    named("CountryDayStatisticsResponseModelToCountryEntity")
                ) {
                    given(map(listOf(countryDayStatisticsResponseModel))).willReturn(countryEntity)
                }
            val localDataSource = declareMock<ILocalDataSource> {
                given(getCountryStatisticsUpdateTimestamp(countryEntity_nameOnly)).willReturn(0L)
            }

            assertThat(testRepository.getStatisticsForCountry(countryEntity_nameOnly))
                .isEqualTo(countryEntity)

            verify(networkDataSource, times(1))
                .getCountryStatistics(countryEntity_nameOnly.name)
            verify(mapper, times(1))
                .map(listOf(countryDayStatisticsResponseModel))
            verify(localDataSource, times(1))
                .storeStatistics(countryEntity)
        }
    }

    @Test
    fun getStatisticsForCountry_3hoursAfterUpdateAndResponseIsNull() {
        runBlockingTest {
            declareMock<TimeProvider> {
                given(getCurrentTime()).willReturn(0L)
                given(
                    hasXMinutesPassedSince(
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyInt()
                    )
                ).willReturn(true)
            }
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getCountryStatistics(countryEntity_nameOnly.name))
                    .willReturn(null)
            }
            val mapper =
                declareMock<Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>>(
                    named("CountryDayStatisticsResponseModelToCountryEntity")
                ) {
                    given(map(listOf(countryDayStatisticsResponseModel))).willReturn(countryEntity)
                }
            val localDataSource = declareMock<ILocalDataSource> {
                given(getCountryStatisticsUpdateTimestamp(countryEntity_nameOnly)).willReturn(0L)
            }

            assertThat(testRepository.getStatisticsForCountry(countryEntity_nameOnly))
                .isEqualTo(countryEntity_nameOnly)

            verify(networkDataSource, times(1))
                .getCountryStatistics(countryEntity_nameOnly.name)
            verify(mapper, times(0))
                .map(listOf(countryDayStatisticsResponseModel))
            verify(localDataSource, times(0))
                .storeStatistics(countryEntity)
        }
    }

    @Test
    fun getStatisticsForCountry_lessThan3hoursAfterUpdate() {
        runBlockingTest {
            declareMock<TimeProvider> {
                given(getCurrentTime()).willReturn(0L)
                given(
                    hasXMinutesPassedSince(
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyLong(),
                        ArgumentMatchers.anyInt()
                    )
                ).willReturn(false)
            }
            val networkDataSource = declareMock<INetworkDataSource>() {
                given(getCountryStatistics(countryEntity_nameOnly.name))
                    .willReturn(null)
            }
            val mapper =
                declareMock<Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>>(
                    named("CountryDayStatisticsResponseModelToCountryEntity")
                ) {
                    given(map(listOf(countryDayStatisticsResponseModel))).willReturn(countryEntity)
                }
            val localDataSource = declareMock<ILocalDataSource> {
                given(getCountryStatisticsUpdateTimestamp(countryEntity_nameOnly)).willReturn(0L)
                given(getCountryWithStatistics(countryEntity_nameOnly)).willReturn(countryEntity)
            }

            assertThat(testRepository.getStatisticsForCountry(countryEntity_nameOnly))
                .isEqualTo(countryEntity)

            verify(networkDataSource, times(0))
                .getCountryStatistics(countryEntity_nameOnly.name)
            verify(mapper, times(0))
                .map(listOf(countryDayStatisticsResponseModel))
            verify(localDataSource, times(0))
                .storeStatistics(countryEntity)
            verify(localDataSource, times(1))
                .getCountryWithStatistics(countryEntity_nameOnly)
        }
    }
}