package com.aungbophyoe.space.mvvmsample.di

import android.content.Context
import androidx.room.Room
import com.aungbophyoe.space.mvvmsample.room.PhotoDao
import com.aungbophyoe.space.mvvmsample.room.PhotoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    @Provides
    fun providePhotoDb(@ApplicationContext context: Context):PhotoDatabase{
        return Room.databaseBuilder(context,PhotoDatabase::class.java,PhotoDatabase.DatabaseName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePhotoDao(photoDatabase: PhotoDatabase):PhotoDao{
        return  photoDatabase.photoDao()
    }
}