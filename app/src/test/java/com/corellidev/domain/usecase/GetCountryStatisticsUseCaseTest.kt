package com.corellidev.domain.usecase

import com.corellidev.covidinfo.di.dataModule
import com.corellidev.covidinfo.di.domainModule
import com.corellidev.covidinfo.di.viewModule
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import com.corellidev.domain.repository.ICountryDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class GetCountryStatisticsUseCaseTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(listOf(viewModule, domainModule, dataModule))
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
            val repository = declareMock<ICountryDataRepository> {
                given(getStatisticsForCountry(inputTestData))
                    .willReturn(expectedResult)
            }
            assertThat(testedUseCase.execute(inputTestData))
                .isEqualTo(expectedResult)

            verify(repository, Mockito.times(1))
                .getStatisticsForCountry(inputTestData)
        }
    }
}