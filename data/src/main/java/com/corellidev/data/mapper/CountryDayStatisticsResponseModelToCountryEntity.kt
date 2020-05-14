package com.corellidev.data.mapper

import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import org.joda.time.DateTime

class CountryDayStatisticsResponseModelToCountryEntity(private val dayStatisticsMapper: Mapper<CountryDayStatisticsResponseModel, DayStatisticsEntity>) :
    Mapper<List<CountryDayStatisticsResponseModel>, CountryEntity>() {

    override fun convert(from: List<CountryDayStatisticsResponseModel>): CountryEntity {
        return if(from.isEmpty()) {
            CountryEntity(
                "",
                emptyList()
            )
        } else {
            CountryEntity(
                from[0].country ?: "",
                dayStatisticsMapper.map(from)
            )
        }
    }
}

class CountryDayStatisticsResponseModelToDayStatisticsEntity :
    Mapper<CountryDayStatisticsResponseModel, DayStatisticsEntity>() {

    override fun convert(from: CountryDayStatisticsResponseModel): DayStatisticsEntity {
        return DayStatisticsEntity(
            DateTime(from.date).toDate(),
            from.confirmed ?: 0,
            from.recovered ?: 0,
            from.deaths ?: 0
        )
    }
}