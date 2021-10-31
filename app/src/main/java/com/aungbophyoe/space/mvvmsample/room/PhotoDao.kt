package com.aungbophyoe.space.mvvmsample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photoCacheEntity: PhotoCacheEntity)

    @Query("SELECT * from photos")
    suspend fun getAll() : List<PhotoCacheEntity>
}