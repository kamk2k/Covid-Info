package com.corellidev.data.mapper

import com.corellidev.data.room.CountryRoomData
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity

class CountryEntityToCountryRoomData : Mapper<CountryEntity, CountryRoomData>() {
    override fun convert(from: CountryEntity): CountryRoomData {
        return CountryRoomData(from.name, 0)
    }
}