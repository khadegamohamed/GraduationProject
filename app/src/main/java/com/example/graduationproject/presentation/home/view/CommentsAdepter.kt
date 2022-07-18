package com.example.graduationproject.presentation.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationproject.databinding.ListItemCommentBinding
import com.example.graduationproject.databinding.ListItemMediainpostBinding
import com.example.graduationproject.presentation.home.model.Comment

class CommentsAdepter(arrayofComment:ArrayList<Comment>,var context: Context):RecyclerView.Adapter<CommentsAdepter.ViewHolder>() {

    class ViewHolder(val commentsBinding: ListItemCommentBinding):RecyclerView.ViewHolder(commentsBinding.root)


    var arrayofComments:ArrayList<Comment> = ArrayList()

    init {
        arrayofComments = arrayofComment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return CommentsAdepter.ViewHolder(
            ListItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var commentsOfPost = arrayofComments.get(position)
        Glide.with(context).load(commentsOfPost.profile_photo).into(holder.commentsBinding.ImageViewProfile)
        holder.commentsBinding.profileName.setText(commentsOfPost.profile_name)
        holder.commentsBinding.theComment.setText(commentsOfPost.caption)
        holder.commentsBinding.timeOfComment.setText(commentsOfPost.time)
    }

    override fun getItemCount(): Int {
      return arrayofComments.size
    }
    fun setData(data:ArrayList<Comment>){
        arrayofComments.clear()
        arrayofComments.addAll(data)
        notifyDataSetChanged()
    }






}