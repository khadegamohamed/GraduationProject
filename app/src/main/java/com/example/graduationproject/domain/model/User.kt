package com.example.graduationproject.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_name")
    var user_name: String?,
    @SerializedName("user_code")
    var user_code: String?
)
