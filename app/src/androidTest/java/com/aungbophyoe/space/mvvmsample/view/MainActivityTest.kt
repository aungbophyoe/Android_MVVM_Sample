package com.aungbophyoe.space.mvvmsample.view

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.aungbophyoe.space.mvvmsample.di.RepositoryModule
import com.aungbophyoe.space.mvvmsample.di.RetrofitModule
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.repository.MainRepository
import com.aungbophyoe.space.mvvmsample.rest.ApiService
import com.aungbophyoe.space.mvvmsample.util.observeOnce
import com.aungbophyoe.space.mvvmsample.viewmodel.MainActivityViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidTest
@UninstallModules(RetrofitModule::class, RepositoryModule::class)
class MainActivityTest{
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    var photoList : ArrayList<Photo> = arrayListOf()

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun testViewModel(){
        activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        activityRule.scenario.onActivity {
            val mainActivityViewModel : MainActivityViewModel = ViewModelProvider(it).get(MainActivityViewModel::class.java)
            it.lifecycleScope.launchWhenResumed {
                val loading = mainActivityViewModel.loading
                delay(5000)
                mainActivityViewModel.dataList.observeOnce { list ->
                    if(list.isNotEmpty()){
                        photoList = list.toCollection(ArrayList())
                    }
                }
                delay(5000)
                assertTrue(photoList.size>0)
            }
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object RetrofitModule {

        @Provides
        fun provideGsonBuilder() : Gson {
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
        fun provideApiService(retrofit: Retrofit.Builder) : ApiService {
            return retrofit
                .build()
                .create(ApiService::class.java)
        }

        @Provides
        fun provideMainRepository(apiService: ApiService): MainRepository {
            return MainRepository(apiService)
        }
    }
}