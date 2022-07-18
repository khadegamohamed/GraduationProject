package com.example.graduationproject.presentation.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationproject.databinding.ListItemMediainpostBinding
import com.example.graduationproject.databinding.ListItemPostsBinding
import com.example.graduationproject.presentation.home.model.PostsPojo


class MediaAdepter(var arrayofImage:ArrayList<String>,var context: Context):RecyclerView.Adapter<MediaAdepter.ViewHolder>() {


    class ViewHolder(val imageBinding:ListItemMediainpostBinding):RecyclerView.ViewHolder(imageBinding.root)




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return MediaAdepter.ViewHolder(
           ListItemMediainpostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var imagesOfPost = arrayofImage.get(position)
        Glide.with(context).load(imagesOfPost).into(holder.imageBinding.MediaItem)
    }

    override fun getItemCount(): Int {
        return arrayofImage.size;
    }




}


