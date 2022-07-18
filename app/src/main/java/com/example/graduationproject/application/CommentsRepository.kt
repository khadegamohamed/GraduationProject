package com.example.graduationproject.application

import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.home.model.Comment
import com.example.graduationproject.presentation.home.model.CommentPojo
import com.example.graduationproject.presentation.home.model.RequestAddComment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CommentsRepository (private val api: ServiceAPI){
    suspend fun addComment(requestOfComment:RequestAddComment,id:String):Response<List<Comment>> =
        withContext(Dispatchers.IO) {
           var respones =  api.addComment(requestOfComment,id)
            return@withContext respones
        }

}