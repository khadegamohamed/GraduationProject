package com.example.graduationproject.presentation.home.view

import android.content.Context
import android.view.View
import com.example.graduationproject.presentation.home.model.Comment
import com.example.graduationproject.presentation.home.model.Like
import com.example.graduationproject.presentation.home.model.ListOfLikes

interface PostsListener {
    fun onClickImages(image:String)
    fun onClickNumberOfLikes(likes:List<Like>)
    fun onClickLike(id:String,postion:Int)
    fun onClickComment(comments:List<Comment>,id:String)
    fun onClickDeletOrEdit(context: Context, view: View,idPost:String)
}