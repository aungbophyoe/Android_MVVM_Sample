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


/*
*  create by aungbophyoe
*  on 10/29/2021
**/

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val tag : String = "MainActivity"
    private var binding : ActivityMainBinding? = null
    private val mainActivityViewModel : MainActivityViewModel by viewModels()
    private val photoRecyclerAdapter by lazy {
        PhotoRecyclerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding!!.apply {
            rvPhotos.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            lifecycleOwner = this@MainActivity
            viewModel = mainActivityViewModel
            rvPhotos.adapter = photoRecyclerAdapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}