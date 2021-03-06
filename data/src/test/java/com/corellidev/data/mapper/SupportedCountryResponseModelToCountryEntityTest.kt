package com.corellidev.data.mapper

import com.corellidev.data.countryEntity_nameOnly
import com.corellidev.data.supportedCountryResponseModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SupportedCountryResponseModelToCountryEntityTest {
    @Test
    fun convert() {
        assertThat(SupportedCountryResponseModelToCountryEntity().convert(supportedCountryResponseModel))
            .isEqualTo(countryEntity_nameOnly)
    }
}