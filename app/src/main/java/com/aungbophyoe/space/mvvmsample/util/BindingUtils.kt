package com.aungbophyoe.space.mvvmsample.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.recyclerAdapter.PhotoRecyclerAdapter
import com.aungbophyoe.space.mvvmsample.rest.response.PhotoNetworkEntity

@BindingAdapter("textTitle")
fun TextView.setTitle(item : Photo){
    text = item.photoTitle
}

@BindingAdapter("image")
fun ImageView.setImage(item: Photo){
    ImageBinderAdapter.setImageUrl(this,item.photoUrl)
}

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<Photo>?) {
    (listView.adapter as PhotoRecyclerAdapter).submitList(items)
}
