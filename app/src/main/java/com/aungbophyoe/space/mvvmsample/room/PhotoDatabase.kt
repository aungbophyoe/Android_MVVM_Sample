package com.aungbophyoe.space.mvvmsample.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PhotoCacheEntity::class],version = 1)
abstract class PhotoDatabase : RoomDatabase() {
    companion object{
        val DatabaseName : String = "photo_database"
    }
    abstract fun photoDao() : PhotoDao
}