package com.example.graduationproject.domain.networking

import com.example.graduationproject.domain.model.LoginResponse
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.presentation.auth.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {

    @POST("login")
    suspend fun login(@Body user: User) : Response<LoginResponse>

    @GET("")
    suspend fun getProfileInfo(): Response<ProfileResponse>
}