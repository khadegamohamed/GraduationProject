package com.example.graduationproject.application

import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.home.model.PostsPojo
import com.example.graduationproject.presentation.newPosts.model.RequestNewPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part

class NewPostRepository (private val api: ServiceAPI){

    suspend fun addNewPost(post:RequestNewPost):Response<PostsPojo> =
        withContext(Dispatchers.IO) {
           var response= api.addNewPost(post)
            return@withContext response
        }
    suspend fun addNewPostWithMedia(caption: String, file: MultipartBody.Part):Response<PostsPojo> =
        withContext(Dispatchers.IO) {
            var response= api.addNewPostWithMedia(caption,file)
            return@withContext response
        }

}