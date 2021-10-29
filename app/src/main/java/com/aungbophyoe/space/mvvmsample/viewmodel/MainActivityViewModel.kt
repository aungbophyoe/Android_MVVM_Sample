package com.aungbophyoe.space.mvvmsample.viewmodel

import android.util.Log
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
    private val repository: MainRepository):ViewModel() {
    private val _data : MutableLiveData<List<Photo>> = MutableLiveData()
    val dataList : LiveData<List<Photo>> get() = _data

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    init {
        viewModelScope.launch {
            _loading.value = true
            repository.getPhotos()
                .onEach { dataState ->
                    when(dataState){
                        is DataState.Success -> {
                            _loading.value = false
                            if(dataState.data.isSuccessful){
                                _data.value = dataState.data.body()
                                Log.d("tag",dataState.data.body().toString())
                            }
                        }
                        is DataState.Loading -> {
                            _loading.value = true
                            _data.value = null
                        }
                        is DataState.Error -> {
                            _loading.value = false
                            _data.value = null
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}