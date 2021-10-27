package com.aungbophyoe.space.mvvmsample.viewmodel

import androidx.lifecycle.*
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.repository.MainRepository
import com.aungbophyoe.space.mvvmsample.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MainRepository):ViewModel() {
    private val _data : MutableLiveData<DataState<Response<List<Photo>>>> = MutableLiveData()
    val dataList : LiveData<DataState<Response<List<Photo>>>> get() = _data
    fun getPhoto(){
        viewModelScope.launch {
            repository.getPhotos()
                .onEach { dataState ->
                    _data.value = dataState
                }
                .launchIn(viewModelScope)
        }
    }
}