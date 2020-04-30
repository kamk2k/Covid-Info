package com.corellidev.data.datasource

import com.corellidev.data.room.CountryRoomDAO
import com.corellidev.data.room.CountryRoomData
import com.corellidev.data.room.DayStatisticsRoomDAO
import com.corellidev.data.room.DayStatisticsRoomData
import com.corellidev.domain.common.Mapper
import com.corellidev.domain.entity.CountryEntity
import com.corellidev.domain.entity.DayStatisticsEntity

class RoomLocalDataSource(
    private val countryRoomDAO: CountryRoomDAO,
    private val dayStatisticsRoomDAO: DayStatisticsRoomDAO,
    private val countryEntityToCountryData: Mapper<CountryEntity, CountryRoomData>,
    private val countryEntityToDayStatisticsDataList: Mapper<CountryEntity, List<DayStatisticsRoomData>>,
    private val countryDataToCountryEntity: Mapper<CountryRoomData, CountryEntity>,
    private val dayStatisticsDataToDayStatisticsEntity: Mapper<DayStatisticsRoomData, DayStatisticsEntity>
) : ILocalDataSource {

    override suspend fun storeCountriesData(countriesData: List<CountryEntity>) {
        countryRoomDAO.insertAllNewItems(countryEntityToCountryData.map(countriesData))
    }

    override suspend fun storeStatistics(countryDataWithStats: CountryEntity) {
        countryRoomDAO.insertOrReplace(countryEntityToCountryData.map(countryDataWithStats))
        dayStatisticsRoomDAO.insertAll(countryEntityToDayStatisticsDataList.map(countryDataWithStats))
    }

    override suspend fun getCountriesList(): List<CountryEntity> {
        return countryDataToCountryEntity.map(countryRoomDAO.getAll())
    }

    override suspend fun getCountryWithStatistics(country: CountryEntity): CountryEntity {
        return CountryEntity(
            country.name,
            dayStatisticsDataToDayStatisticsEntity.map(
                dayStatisticsRoomDAO.getStatisticsForCountry(country.name)
            )
        )
    }

    override suspend fun getCountryStatisticsUpdateTimestamp(country: CountryEntity): Long =
        countryRoomDAO.get(country.name).timestamp
}