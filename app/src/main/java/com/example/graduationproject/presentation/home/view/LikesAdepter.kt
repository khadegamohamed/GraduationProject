package com.example.graduationproject.presentation.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationproject.databinding.ListItemMediainpostBinding
import com.example.graduationproject.databinding.ListitemWhoislikeBinding
import com.example.graduationproject.presentation.home.model.Like

class LikesAdepter(var listOfLikes:List<Like>,var context:Context):RecyclerView.Adapter<LikesAdepter.ViewHolder>(){

    class ViewHolder(val LikesBinding:ListitemWhoislikeBinding):RecyclerView.ViewHolder(LikesBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LikesAdepter.ViewHolder(ListitemWhoislikeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    var theLike= listOfLikes.get(position)
        holder.LikesBinding.profileName.setText(theLike.profile_name)
        Glide.with(context).load(theLike.profile_photo).into(holder.LikesBinding.profileImage)
    }

    override fun getItemCount(): Int {
     return listOfLikes.size
    }



}