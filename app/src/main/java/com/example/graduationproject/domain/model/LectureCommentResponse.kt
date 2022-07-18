package com.example.graduationproject.domain.model

class LectureCommentResponse : ArrayList<LectureCommentResponseItem>()

data class LectureCommentResponseItem(
    val _id: String,
    val caption: String,
    val profile_name: String,
    val profile_photo: String,
    val time: String,
    val user_id: String
)