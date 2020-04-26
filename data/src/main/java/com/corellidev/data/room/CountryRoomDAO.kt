package com.corellidev.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryRoomDAO {

    @Query("SELECT * FROM ${AppDatabase.COUNTRY_DATA}")
    fun getAll(): List<CountryRoomData>

    @Query("SELECT * FROM ${AppDatabase.COUNTRY_DATA} WHERE ${CountryRoomData.NAME_COLUMN} = :countryName")
    fun get(countryName: String) : CountryRoomData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllNewItems(items: List<CountryRoomData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(data: CountryRoomData)
}