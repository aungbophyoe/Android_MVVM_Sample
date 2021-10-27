package com.aungbophyoe.space.mvvmsample.di

import com.aungbophyoe.space.mvvmsample.rest.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideGsonBuilder() : Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    fun provideRetrofitBuilder(gson: Gson) : Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    fun provideApiService(retrofit: Retrofit.Builder) : ApiService{
        return retrofit
            .build()
            .create(ApiService::class.java)
    }
}