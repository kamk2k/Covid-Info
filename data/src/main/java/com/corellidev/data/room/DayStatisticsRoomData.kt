package com.corellidev.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    primaryKeys = [DayStatisticsRoomData.COUNTRY_NAME_COLUMN, DayStatisticsRoomData.DATE_COLUMN],
    tableName = AppDatabase.DAY_STATISTICS_DATA
)
data class DayStatisticsRoomData(
    @ColumnInfo(name = COUNTRY_NAME_COLUMN)
    val countryName: String,
    @ColumnInfo(name = DATE_COLUMN)
    val date: Long,
    @ColumnInfo(name = CONFIRMED_COLUMN)
    val confirmed: Int,
    @ColumnInfo(name = RECOVERED_COLUMN)
    val recovered: Int,
    @ColumnInfo(name = DEATHS_COLUMN)
    val deaths: Int
) {
    companion object {
        const val COUNTRY_NAME_COLUMN = "country_name"
        const val DATE_COLUMN = "date"
        const val CONFIRMED_COLUMN = "confirmed"
        const val RECOVERED_COLUMN = "recovered"
        const val DEATHS_COLUMN = "deaths"
    }
}