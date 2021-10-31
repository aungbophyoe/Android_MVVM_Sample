package com.aungbophyoe.space.mvvmsample.repository


import com.aungbophyoe.space.mvvmsample.mapper.CacheMapper
import com.aungbophyoe.space.mvvmsample.mapper.NetworkMapper
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.rest.ApiService
import com.aungbophyoe.space.mvvmsample.room.PhotoDao
import com.aungbophyoe.space.mvvmsample.util.DataState
import com.aungbophyoe.space.mvvmsample.util.performGetOperation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService,
                                         private val photoDao: PhotoDao,
                                         private val cacheMapper: CacheMapper,
                                         private val networkMapper: NetworkMapper) {
    suspend fun getPhotos() : Flow<DataState<List<Photo>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val response = apiService.getPhotos()
            if(response.isSuccessful){
                val networkData = response.body()
                val dataList = networkMapper.mapFromEntityList(networkData!!)
                for (data in dataList){
                    photoDao.insertAll(cacheMapper.mapToEntity(data))
                }
                val cacheData = photoDao.getAll()
                emit(DataState.Success(cacheMapper.mapFromEntityList(cacheData)))
            }
        }catch (e:Exception){
            val cacheData = photoDao.getAll()
            emit(DataState.Error(e,cacheMapper.mapFromEntityList(cacheData)))
        }
    }
}