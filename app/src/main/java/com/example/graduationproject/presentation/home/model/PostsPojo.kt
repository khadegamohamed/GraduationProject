package com.example.graduationproject.presentation.home.model

data class PostsPojo(
    val __v: Int,
    val _id: String,
    val caption: String,
    val comments: List<Comment>,
    val createdAt: String,
    val image: String,
    val likes: List<Like>,
    var likesNum:Int,
    val postedBy: String,
    val profile_name: String,
    val profile_photo: String,
    val vidoe: String
)