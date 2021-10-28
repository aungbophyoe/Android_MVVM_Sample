package com.aungbophyoe.space.mvvmsample.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.aungbophyoe.space.mvvmsample.model.Photo

@BindingAdapter("textTitle")
fun TextView.setTitle(item : Photo){
    text = item.title
}

@BindingAdapter("image")
fun ImageView.setImage(item: Photo){
    ImageBinderAdapter.setImageUrl(this,item.url)
}
