package com.aungbophyoe.space.mvvmsample.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.recyclerAdapter.PhotoRecyclerAdapter

@BindingAdapter("textTitle")
fun TextView.setTitle(item : Photo){
    text = item.title
}

@BindingAdapter("image")
fun ImageView.setImage(item: Photo){
    ImageBinderAdapter.setImageUrl(this,item.url)
}

@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<Photo>?) {
    (listView.adapter as PhotoRecyclerAdapter).submitList(items)
}
