package com.aditya.belajarandroid

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.belajarandroid.databinding.ListPhotosBinding
import com.bumptech.glide.Glide

class PhotoAdapter : ListAdapter<Photos,PhotoAdapter.ViewHolder>(DIF_CALLBACK) {


    inner class ViewHolder (
        private val binding: ListPhotosBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(data:Photos){
        binding.txtPhotos.text = data.title
        Glide.with(itemView.context)
        .load(data.thumbnailUrl)
        .error(android.R.color.darker_gray)
        .into(binding.imgPhotos)

        }
    }
    companion object {
        val DIF_CALLBACK: DiffUtil.ItemCallback<Photos> =
            object : DiffUtil.ItemCallback<Photos>() {
                override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListPhotosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}