package com.aungbophyoe.space.mvvmsample.di

import com.aungbophyoe.space.mvvmsample.repository.MainRepository
import com.aungbophyoe.space.mvvmsample.rest.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMainRepository(apiService: ApiService):MainRepository{
        return MainRepository(apiService)
    }
}