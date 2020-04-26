package com.corellidev.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AppDatabase.COUNTRY_DATA)
data class CountryRoomData(
    @PrimaryKey @ColumnInfo(name = NAME_COLUMN)
    val name: String,
    @ColumnInfo(name = TIMESTAMP_COLUMN)
    val timestamp: Long = 0
) {
    companion object {
        const val NAME_COLUMN = "name"
        const val TIMESTAMP_COLUMN = "timestamp"
    }
}

