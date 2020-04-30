package com.corellidev.data.mapper

import com.corellidev.data.countryEntity
import com.corellidev.data.dayStatisticsRoomData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountryEntityToDayStatisticsRoomDataListTest {
    @Test
    fun convert() {
        assertThat(CountryEntityToDayStatisticsRoomDataList().convert(countryEntity))
            .isEqualTo(listOf(dayStatisticsRoomData))
    }
}