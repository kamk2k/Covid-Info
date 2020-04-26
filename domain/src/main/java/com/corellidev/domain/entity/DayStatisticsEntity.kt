package com.corellidev.domain.entity

import java.util.*

data class DayStatisticsEntity(
    val date: Date,
    val confirmed: Int,
    val recovered: Int,
    val deaths: Int
)