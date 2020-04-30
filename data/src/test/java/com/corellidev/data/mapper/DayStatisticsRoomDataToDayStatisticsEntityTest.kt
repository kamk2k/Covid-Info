package com.corellidev.data.mapper

import com.corellidev.data.dayStatisticsEntity
import com.corellidev.data.dayStatisticsRoomData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DayStatisticsRoomDataToDayStatisticsEntityTest {
    @Test
    fun convert() {
        assertThat(DayStatisticsRoomDataToDayStatisticsEntity().convert(dayStatisticsRoomData))
            .isEqualTo(dayStatisticsEntity)
    }
}