package com.example.graduationproject.presentation.home.model

data class Comment(
    val _id: String,
    val caption: String,
    val profile_name: String,
    val profile_photo: String,
    val time: String,
    val user_id: String
)