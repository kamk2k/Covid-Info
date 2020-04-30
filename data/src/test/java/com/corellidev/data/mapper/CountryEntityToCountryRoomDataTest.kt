package com.corellidev.data.mapper

import com.corellidev.data.countryEntity
import com.corellidev.data.countryRoomData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountryEntityToCountryRoomDataTest {
    @Test
    fun convert() {
        assertThat(CountryEntityToCountryRoomData().convert(countryEntity))
            .isEqualTo(countryRoomData)
    }
}