package com.corellidev.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DayStatisticsRoomDAO {

    @Query("SELECT * FROM ${AppDatabase.DAY_STATISTICS_DATA} WHERE ${DayStatisticsRoomData.COUNTRY_NAME_COLUMN} = :countryName")
    fun getStatisticsForCountry(countryName: String): List<DayStatisticsRoomData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<DayStatisticsRoomData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: DayStatisticsRoomData)
}