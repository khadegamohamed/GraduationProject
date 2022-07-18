package com.example.graduationproject.domain.networking

import com.example.graduationproject.domain.model.LoginResponse
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.presentation.auth.model.User
import com.example.graduationproject.presentation.calender.model.DateEventRequest
import com.example.graduationproject.presentation.calender.model.EventPojo
import com.example.graduationproject.presentation.calender.model.EventRequest
import com.example.graduationproject.presentation.home.model.*
import com.example.graduationproject.presentation.newPosts.model.RequestEditPost
import com.example.graduationproject.presentation.newPosts.model.RequestNewPost
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ServiceAPI {

    @POST("login")
    suspend fun login(@Body user: User) : Response<LoginResponse>

    @GET("profile")
    suspend fun getProfileInfo(): Response<ProfileResponse>

    @GET("home")
    suspend fun getAllPosts(): Response<List<PostsPojo>>

    @PUT("post/{id}/like")
    suspend fun addLike(@Path("id")id:String):Response<LikePojo>

    @POST("addPost")
    suspend fun addNewPost(@Body post: RequestNewPost):Response<PostsPojo>

    @Multipart
    @POST("addPost")
    suspend fun addNewPostWithMedia(@Part("caption") caption: String
                                    , @Part file:MultipartBody.Part ):Response<PostsPojo>

    @POST("post/{id}/comment")
    suspend fun addComment(@Body requestOfAddComment:RequestAddComment,@Path("id")id:String ):Response<List<Comment>>

    @PUT("post/{id}/edit")
    suspend fun editPost(@Body requestEditPost: RequestEditPost,@Path("id")id:String ):Response<String>

    @DELETE("post/{id}/delete")
    suspend fun deletePost(@Path("id")id:String):Response<String>

    @GET("allEvents")
    suspend fun getAllEvents():Response<List<EventPojo>>

    @POST("addEvent")
    suspend fun addEvent(@Body eventRequest: EventRequest):Response<EventRequest>

    @GET("events")
    suspend fun getDateEvent(@Body dateEventRequest: DateEventRequest):Response<List<EventPojo>>


}