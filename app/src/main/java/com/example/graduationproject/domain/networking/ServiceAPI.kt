package com.example.graduationproject.domain.networking

import com.example.graduationproject.domain.model.*
import com.example.graduationproject.presentation.auth.model.User
import com.example.graduationproject.presentation.calender.model.DateEventRequest
import com.example.graduationproject.presentation.calender.model.EventPojo
import com.example.graduationproject.presentation.calender.model.EventRequest
import com.example.graduationproject.presentation.home.model.*
import com.example.graduationproject.presentation.newPosts.model.RequestEditPost
import com.example.graduationproject.presentation.newPosts.model.RequestNewPost

import com.example.graduationproject.presentation.lectures.model.Comment
import com.example.graduationproject.presentation.profile.model.Profile
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*



interface ServiceAPI {

    @POST("login")
    suspend fun login(@Body user: User): Response<LoginResponse>

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


    @PUT("profile/{id}/edit/name")
    suspend fun updateProfileName(
        @Path("id") id: String,
        @Body profile:Profile): Response<ResponseBody>

    @Multipart
    @PUT("profile/{id}/edit/photo")
    suspend fun updateProfileImage(
        @Path("id") id: String,
        @Part file: MultipartBody.Part): Response<UploadLectureResponse>

    // test
    @Multipart
    @POST("test")
    suspend fun setImage(@Part file: MultipartBody.Part): Response<ResponseBody>


    @GET("AllSubjects")
    suspend fun getAllSubjects(): Response<List<SubjectResponse>>


    @Multipart
    @POST("subject/{id}/addLecture")
   suspend fun uploadPdfFile(@Part file: MultipartBody.Part)
                                  : Response<UploadLectureResponse>

    @GET("subject/{id}")
    suspend fun getLectures(@Path("id") id: String): Response<LectureResponse>

    @POST("lecture/{id}/addComment")
    suspend fun addLectureComment(
        @Path("id") id: String,
        @Body comment: Comment
    ): Response<ResponseBody>

    @GET("lecture/{id}/allComments")
    suspend fun gatAllComments(@Path("id") id: String)
            : Response<List<LectureCommentResponseItem>>


}