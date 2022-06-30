package com.example.graduationproject.domain.networking

import com.example.graduationproject.domain.model.LoginResponse
import com.example.graduationproject.domain.model.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {

    @POST("login")
    suspend fun login(@Body user: User) : Response<LoginResponse>

    @GET("")
    suspend fun getSecretInfo(token:String): Response<ResponseBody>
}