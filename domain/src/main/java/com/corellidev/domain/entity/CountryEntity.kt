package com.corellidev.domain.entity

data class CountryEntity(val name: String, val statistics: List<DayStatisticsEntity> = emptyList())