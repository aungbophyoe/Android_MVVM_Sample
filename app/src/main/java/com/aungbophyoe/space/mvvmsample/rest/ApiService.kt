package com.aungbophyoe.space.mvvmsample.rest

import com.aungbophyoe.space.mvvmsample.model.Photo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("photos")
    suspend fun getPhotos() : Response<List<Photo>>
}