package com.aungbophyoe.space.mvvmsample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.aungbophyoe.space.mvvmsample.R
import com.aungbophyoe.space.mvvmsample.databinding.ActivityMainBinding
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.recyclerAdapter.PhotoRecyclerAdapter
import com.aungbophyoe.space.mvvmsample.util.DataState
import com.aungbophyoe.space.mvvmsample.util.showOrGone
import com.aungbophyoe.space.mvvmsample.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val tag : String = "MainActivity"
    private var binding : ActivityMainBinding? = null
    private val mainActivityViewModel : MainActivityViewModel by viewModels()
    private val photoRecyclerAdapter by lazy {
        PhotoRecyclerAdapter(this)
    }
    private val dataList : ArrayList<Photo>  = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding!!.apply {
            rvPhotos.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            photoRecyclerAdapter.submitList(dataList)
            rvPhotos.adapter = photoRecyclerAdapter
            observeApiData()
            mainActivityViewModel.getPhoto()
        }
    }

    private fun observeApiData(){
        try {
            binding!!.apply {
                mainActivityViewModel.dataList.observe(this@MainActivity,{
                    it?.let { dataState ->
                        when(dataState) {
                            is DataState.Loading ->{
                                rvPhotos.showOrGone(false)
                                tvNoData.showOrGone(false)
                                progressBar.showOrGone(true)

                            }
                            is DataState.Error ->{
                                rvPhotos.showOrGone(false)
                                tvNoData.showOrGone(true)
                                progressBar.showOrGone(false)
                            }
                            is DataState.Success<Response<List<Photo>>> -> {
                                rvPhotos.showOrGone(true)
                                tvNoData.showOrGone(false)
                                progressBar.showOrGone(false)
                                dataState.data.let { response ->
                                    if(response.isSuccessful){
                                        if(dataList.size>0){
                                            dataList.clear()
                                        }
                                        val list = response.body()!!
                                        Log.d(tag,"$list")
                                        dataList.addAll(list)
                                        photoRecyclerAdapter.submitList(dataList)
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }catch (e:Exception){
            Log.d(tag,e.message.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}