package com.example.graduationproject.domain.model

data class LoginResponse(
    val token: String,
    val isSuccessful: Boolean,
    val massage: String,
    val role: String
)
