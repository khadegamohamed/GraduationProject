package com.example.graduationproject.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubjectResponse(
    @SerializedName("_id")
    @Expose
    val _id: String,
    @SerializedName("subject_name")
    @Expose
    val subject_name: String,
)
