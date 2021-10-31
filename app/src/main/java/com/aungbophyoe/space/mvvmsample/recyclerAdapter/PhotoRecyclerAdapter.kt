package com.aungbophyoe.space.mvvmsample.recyclerAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aungbophyoe.space.mvvmsample.databinding.RvPhotosItemBinding
import com.aungbophyoe.space.mvvmsample.model.Photo
import com.aungbophyoe.space.mvvmsample.rest.response.PhotoNetworkEntity

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


    class ViewHolder private constructor(val binding: RvPhotosItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Photo){
            binding.photo = item
            binding.executePendingBindings()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
                val binding = RvPhotosItemBinding.inflate(layoutInflater ,parent,false)
                val viewHolder = ViewHolder(binding)
                return viewHolder
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = getItem(position)
            holder.bind(item)
        }catch (e:Exception){
            Log.d("recycler",e.message.toString())
        }
    }
}