package com.example.graduationproject.presentation.home.model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import androidx.versionedparcelable.VersionedParcelize
import java.io.Serializable


data class ListOfImages (var images:ArrayList<String>) :Serializable


