package com.corellidev.data.mapper

import com.corellidev.data.room.DayStatisticsRoomData
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.DayStatisticsEntity
import java.util.*

class DayStatisticsRoomDataToDayStatisticsEntity : Mapper<DayStatisticsRoomData, DayStatisticsEntity>() {
    override fun convert(from: DayStatisticsRoomData): DayStatisticsEntity {
        return DayStatisticsEntity(Date(from.date), from.confirmed, from.recovered, from.deaths)
    }
}