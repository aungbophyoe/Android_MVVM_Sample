package com.aungbophyoe.space.mvvmsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    @Expose
    var id : Int,
    @SerializedName("title")
    @Expose
    var title  : String,
    @SerializedName("url")
    @Expose
    var url : String
)