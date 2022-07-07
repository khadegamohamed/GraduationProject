package com.example.graduationproject.domain.networking

import com.example.graduationproject.domain.model.LoginResponse
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.presentation.auth.model.User
import com.example.graduationproject.presentation.lectures.model.LectureCommentModel
import com.example.graduationproject.presentation.lectures.model.LectureModel
import com.example.graduationproject.presentation.subjects.model.SubjectModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServiceAPI {

    @POST("login")
    suspend fun login(@Body user: User) : Response<LoginResponse>


    @GET("profile")
    suspend fun getProfileInfo(): Response<ProfileResponse>

    @GET("")
    suspend fun getFirstTermSubjects(): Response<SubjectModel>

    @GET("")
    suspend fun getSecondTermSubjects(): Response<SubjectModel>

    @POST("")
    suspend fun uploadLectures(@Body lectureModel: LectureModel): Response<ResponseBody>
    @GET("")
    suspend fun getLectures(): Response<LectureModel>
 //   @POST("")
//    suspend fun uploadLectureComments(@Body lectureCommentModel: LectureCommentModel):
}