package com.example.graduationproject.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("user_name")
    @Expose
    var user_name: String,
    @SerializedName("profile_name")
    @Expose
    var profile_name: String,
    @SerializedName("profile_photo")
    @Expose
    var profile_photo: String,
    @SerializedName("_id")
    @Expose
    var _id: String,
    @SerializedName("role")
    @Expose
    var role: String? = null,
)


