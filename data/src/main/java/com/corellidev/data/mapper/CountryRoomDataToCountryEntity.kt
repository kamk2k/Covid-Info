package com.corellidev.data.mapper

import com.corellidev.data.room.CountryRoomData
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity

class CountryRoomDataToCountryEntity : Mapper<CountryRoomData, CountryEntity>() {
    override fun convert(from: CountryRoomData): CountryEntity {
        return CountryEntity(from.name)
    }
}