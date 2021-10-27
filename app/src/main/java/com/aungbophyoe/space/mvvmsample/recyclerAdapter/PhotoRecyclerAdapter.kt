package com.aungbophyoe.space.mvvmsample.recyclerAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mvvmsample.R
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.util.ImageBinderAdapter
import kotlinx.android.synthetic.main.rv_photos_item.view.*
import javax.inject.Inject

class PhotoRecyclerAdapter constructor(private val context: Context):ListAdapter<Photo,PhotoRecyclerAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

    }
) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView = view.ivPhoto
        val title = view.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = inflater.inflate(R.layout.rv_photos_item, parent, false)
        val viewHolder = ViewHolder(inflateView)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val photo = currentList[position]
            ImageBinderAdapter.setImageUrl(holder.imageView,photo.url)
            holder.title.text = photo.title
        }catch (e:Exception){
            Log.d("recycler",e.message.toString())
        }
    }
}