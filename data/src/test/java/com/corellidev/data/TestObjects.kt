package com.corellidev.data

import com.corellidev.data.model.CountryDayStatisticsResponseModel
import com.corellidev.data.model.SupportedCountryResponseModel
import com.corellidev.data.room.CountryRoomData
import com.corellidev.data.room.DayStatisticsRoomData
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity
import org.joda.time.DateTime

val supportedCountryResponseModel = SupportedCountryResponseModel("Poland", "poland", "PL")

val countryDayStatisticsResponseModel = CountryDayStatisticsResponseModel(
    country = "Poland",
    confirmed = 6674,
    deaths = 232,
    recovered = 439,
    date = "2020-04-12T00:00:00Z"
)

val dayStatisticsEntity =
    DayStatisticsEntity(DateTime("2020-04-12T00:00:00Z").toDate(), 6674, 439, 232)

val countryEntity = CountryEntity(
    "Poland",
    listOf(dayStatisticsEntity)
)

val countryEntity_nameOnly = CountryEntity("Poland")

val countryRoomData = CountryRoomData("Poland")

val dayStatisticsRoomData =
    DayStatisticsRoomData("Poland", DateTime("2020-04-12T00:00:00Z").millis, 6674, 439, 232)