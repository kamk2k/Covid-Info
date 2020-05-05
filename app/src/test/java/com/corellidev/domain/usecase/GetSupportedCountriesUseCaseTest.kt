package com.corellidev.domain.usecase

import com.corellidev.covidinfo.di.dataModule
import com.corellidev.covidinfo.di.domainModule
import com.corellidev.covidinfo.di.viewModule
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.repository.ICountryDataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions
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

@ExperimentalCoroutinesApi
class GetSupportedCountriesUseCaseTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(listOf(viewModule, domainModule, dataModule))
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
            val repository = declareMock<ICountryDataRepository> {given(getSupportedCountries()).willReturn(expectedResult)}

            Assertions.assertThat(testedUseCase.execute())
                .isEqualTo(expectedResult)

            Mockito.verify(repository, Mockito.times(1))
                .getSupportedCountries()
        }
    }
}