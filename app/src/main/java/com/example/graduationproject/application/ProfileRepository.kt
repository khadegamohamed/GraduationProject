package com.example.graduationproject.application

import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.profile.model.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class ProfileRepository(private val api: ServiceAPI) {

    suspend fun getProfileInfo() =
        withContext(Dispatchers.IO) {
            api.getProfileInfo()
        }
    suspend fun updateProfileName(id:String,profileName:Profile) =
        withContext(Dispatchers.IO){
            api.updateProfileName(id, profileName)
        }
    suspend fun updateProfileImage(id:String,imageFile:MultipartBody.Part)=
        withContext(Dispatchers.IO){
            api.updateProfileImage(id,imageFile)
        }

}