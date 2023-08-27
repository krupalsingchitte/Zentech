package com.kr.zentech.Adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kr.zentech.R

class ImageSliderAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageSlideViewHolder>() {

    inner class ImageSlideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.slider_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSlideViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_slider, parent, false)
        return ImageSlideViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageSlideViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(imageUrls[position]).into(holder.imageView)
    }

    override fun getItemCount() = imageUrls.size
}
