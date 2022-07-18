package com.example.graduationproject.application

import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.home.model.RequestAddComment
import com.example.graduationproject.presentation.newPosts.model.RequestEditPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class EditPostRepository (private val api: ServiceAPI){

    suspend fun editPost(requestEditPost: RequestEditPost,id:String):Response<String> =
        withContext(Dispatchers.IO) {
            var response = api.editPost(requestEditPost,id)
            return@withContext response
        }

}