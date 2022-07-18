package com.example.graduationproject.presentation.newPosts.model

import com.google.gson.annotations.SerializedName

data class RequestNewPost (
    @SerializedName("token")
    var token:String?,
    @SerializedName("caption")
    var thePost:String
    )