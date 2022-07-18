package com.example.graduationproject.presentation.home.model

import com.google.gson.annotations.SerializedName

data class RequestAddComment (

    @SerializedName("caption")
      var theComment:String,

   @SerializedName("token")
    var token:String?

)
