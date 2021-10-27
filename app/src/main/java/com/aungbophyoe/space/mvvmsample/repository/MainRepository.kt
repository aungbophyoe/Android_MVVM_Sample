package com.aungbophyoe.space.mvvmsample.repository

import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.rest.ApiService
import com.aungbophyoe.space.mvvmsample.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPhotos() : Flow<DataState<Response<List<Photo>>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val data = apiService.getPhotos()
            emit(DataState.Success(data))
        }catch (e:Exception){
            emit(DataState.Error(e))
        }
    }
}