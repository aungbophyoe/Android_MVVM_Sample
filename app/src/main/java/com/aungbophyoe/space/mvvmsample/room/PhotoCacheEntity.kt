package com.aungbophyoe.space.mvvmsample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name="title")
    val title : String,
    @ColumnInfo(name = "url")
    val url :String
) {
}