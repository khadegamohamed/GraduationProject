package com.example.graduationproject.presentation.calender.model

import com.google.gson.annotations.SerializedName

data class EventRequest (
    @SerializedName("event_name")
    var event_name:String,

    @SerializedName("from")
    var from:String,

    @SerializedName("to")
      var to:String
)