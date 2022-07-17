package com.example.graduationproject.application

import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.lectures.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LectureCommentRepository(private val api:ServiceAPI) {

    suspend fun addComment(id: String, comment: Comment)=
        withContext(Dispatchers.IO){
            api.addLectureComment(id, comment)
        }

    suspend fun getAllComments(lectureId:String) =
        withContext(Dispatchers.IO){
            api.gatAllComments(lectureId)
        }
}