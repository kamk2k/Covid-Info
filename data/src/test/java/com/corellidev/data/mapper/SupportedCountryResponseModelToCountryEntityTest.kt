package com.corellidev.data.mapper

import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.domain.entity.CountryEntity
import org.junit.Test
import org.assertj.core.api.Assertions.*

class SupportedCountryResponseModelToCountryEntityTest {

    val testData = SupportedCountryResponseModel("Poland", "poland", "PL")
    val expectedResult = CountryEntity("Poland", emptyList())

    @Test
    fun convert() {
        assertThat(SupportedCountryResponseModelToCountryEntity().convert(testData))
            .isEqualTo(expectedResult)
    }
}