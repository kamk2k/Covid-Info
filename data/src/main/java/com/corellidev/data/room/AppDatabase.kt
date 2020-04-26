package com.corellidev.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountryRoomData::class, DayStatisticsRoomData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME: String = "app_db"
        const val COUNTRY_DATA: String = "country_data"
        const val DAY_STATISTICS_DATA: String = "day_statistics_data"
    }

    abstract fun getCountryRoomDAO(): CountryRoomDAO
    abstract fun getDayStatisticsRoomDAO(): DayStatisticsRoomDAO

}