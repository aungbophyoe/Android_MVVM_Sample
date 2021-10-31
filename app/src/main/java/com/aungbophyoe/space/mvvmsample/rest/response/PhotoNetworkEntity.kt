package com.aungbophyoe.space.mvvmsample.rest.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoNetworkEntity(
    @SerializedName("id")
    @Expose
    var id : Int,
    @SerializedName("title")
    @Expose
    var title  : String,
    @SerializedName("url")
    @Expose
    var url : String
) {
}