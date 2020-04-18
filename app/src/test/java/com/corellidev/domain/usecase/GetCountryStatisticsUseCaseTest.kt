package com.corellidev.domain.usecase

import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToCountryEntity
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToDayStatisticsEntity
import com.corellidev.data.mapper.SupportedCountryResponseModelToCountryEntity
import com.corellidev.data.repository.CountryDataRepository
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import com.corellidev.domain.repository.ICountryDataRepository
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.joda.time.DateTime
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class GetCountryStatisticsUseCaseTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(module {
            factory { GetSupportedCountriesUseCase(get()) }
            factory { GetCountryStatisticsUseCase(get()) }
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

    private val testedUseCase by inject<GetCountryStatisticsUseCase>()
    private val inputTestData = CountryEntity("poland")
    private val expectedResult = CountryEntity(
        "Poland",
        listOf(
            DayStatisticsEntity(DateTime("2020-04-12T00:00:00Z").toDate(), 6674, 439, 232),
            DayStatisticsEntity(DateTime("2020-04-13T00:00:00Z").toDate(), 6934, 487, 245),
            DayStatisticsEntity(DateTime("2020-04-14T00:00:00Z").toDate(), 7202, 618, 263)
        )
    )

    @Test
    fun execute() {
        runBlockingTest {
            Assertions.assertThat(testedUseCase.execute(inputTestData))
                .isEqualTo(expectedResult)
        }
    }
}