package com.corellidev.domain.usecase

import com.corellidev.data.datasource.INetworkDataSource
import com.corellidev.data.datasource.MockNetworkDataSource
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToCountryEntity
import com.corellidev.data.mapper.CountryDayStatisticsResponseModelToDayStatisticsEntity
import com.corellidev.data.mapper.SupportedCountryResponseModelToCountryEntity
import com.corellidev.data.repository.CountryDataRepository
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

class GetSupportedCountriesUseCaseTest : KoinTest {

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

    private val testedUseCase by inject<GetSupportedCountriesUseCase>()
    private val expectedResult = listOf(
        CountryEntity("Poland"),
        CountryEntity("Italy"),
        CountryEntity("China"),
        CountryEntity("Spain")
    )

    @Test
    fun execute() {
        runBlockingTest {
            Assertions.assertThat(testedUseCase.execute())
                .isEqualTo(expectedResult)
        }
    }
}