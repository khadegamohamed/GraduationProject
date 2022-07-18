package com.example.graduationproject.presentation.calender.model

data class EventPojo(
    val __v: Int,
    val _id: String,
    val doctorId: DoctorIdX,
    val event_name: String,
    val fromDate: String,
    val fromTime: String,
    val subject_name: String,
    val toDate: String,
    val toTime: String
)