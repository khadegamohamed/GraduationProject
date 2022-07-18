package com.example.graduationproject.presentation.newPosts.model

import com.google.gson.annotations.SerializedName

data class RequestEditPost (
                             @SerializedName("caption")
                             var thePost:String,

                             @SerializedName("token")
                             var token:String?)