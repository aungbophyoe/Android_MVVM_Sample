package com.aungbophyoe.space.mvvmsample.di

import com.aungbophyoe.space.mvvmsample.mapper.CacheMapper
import com.aungbophyoe.space.mvvmsample.mapper.NetworkMapper
import com.aungbophyoe.space.mvvmsample.repository.MainRepository
import com.aungbophyoe.space.mvvmsample.rest.ApiService
import com.aungbophyoe.space.mvvmsample.room.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMainRepository(apiService: ApiService,
                              photoDao: PhotoDao,
                              cacheMapper: CacheMapper,
                              networkMapper: NetworkMapper):MainRepository{
        return MainRepository(apiService,photoDao, cacheMapper, networkMapper)
    }
}