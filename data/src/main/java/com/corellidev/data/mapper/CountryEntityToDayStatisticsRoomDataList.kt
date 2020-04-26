package com.corellidev.data.mapper

import com.corellidev.data.room.DayStatisticsRoomData
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity

class CountryEntityToDayStatisticsRoomDataList :
    Mapper<CountryEntity, List<DayStatisticsRoomData>>() {
    override fun convert(from: CountryEntity): List<DayStatisticsRoomData> {
        return from.statistics.map { dayStatisticsEntity ->
            DayStatisticsRoomData(
                from.name,
                dayStatisticsEntity.date.time,
                dayStatisticsEntity.confirmed,
                dayStatisticsEntity.recovered,
                dayStatisticsEntity.deaths
            )
        }
    }
}