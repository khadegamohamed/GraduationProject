package com.example.graduationproject.presentation.home.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class ListOfComments (var listOfComments:List<Comment>,var idOfPost:String) : Serializable