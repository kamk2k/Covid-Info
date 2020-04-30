package com.corellidev.data.mapper

import com.corellidev.data.countryEntity_nameOnly
import com.corellidev.data.countryRoomData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountryRoomDataToCountryEntityTest {
    @Test
    fun convert() {
        assertThat(CountryRoomDataToCountryEntity().convert(countryRoomData))
            .isEqualTo(countryEntity_nameOnly)
    }
}