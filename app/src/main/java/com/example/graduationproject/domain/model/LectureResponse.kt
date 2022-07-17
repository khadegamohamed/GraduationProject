package com.example.graduationproject.domain.model

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize


data class LectureResponse(
    val __v: Int,
    val _id: String,
    val doctorId: String,
    val lectures: List<Lecture>,
    val subject_name: String
)
@Parcelize
data class Lecture(
    val _id: String,
    val `file`: String,
    val fileName: String
):Parcelable