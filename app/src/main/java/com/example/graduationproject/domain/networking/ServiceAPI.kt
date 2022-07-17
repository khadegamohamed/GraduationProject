package com.example.graduationproject.domain.networking

import com.example.graduationproject.domain.model.*
import com.example.graduationproject.presentation.auth.model.User
import com.example.graduationproject.presentation.lectures.model.Comment
import com.example.graduationproject.presentation.lectures.model.LectureModel
import com.example.graduationproject.presentation.profile.model.Profile
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import okhttp3.RequestBody
import retrofit2.http.*


interface ServiceAPI {

    @POST("login")
    suspend fun login(@Body user: User): Response<LoginResponse>


    @GET("profile")
    suspend fun getProfileInfo(): Response<ProfileResponse>

    @PUT("profile/{id}/edit/name")
    suspend fun updateProfileName(
        @Path("id") id: String,
        @Body profile:Profile): Response<ResponseBody>

    @Multipart
    @PUT("profile/{id}/edit/photo")
    suspend fun updateProfileImage(
        @Path("id") id: String,
        @Part file: MultipartBody.Part): Response<ResponseBody>

    @GET("AllSubjects")
    suspend fun getAllSubjects(): Response<List<SubjectResponse>>


    @Multipart
    @POST("")
    fun uploadPdfFile(@PartMap map: Map<String, RequestBody>): Response<ResponseBody>

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