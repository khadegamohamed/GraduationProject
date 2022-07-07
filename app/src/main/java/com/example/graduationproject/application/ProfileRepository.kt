package com.example.graduationproject.application

import com.example.graduationproject.domain.networking.ServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val api: ServiceAPI) {

    suspend fun getProfileInfo() =
        withContext(Dispatchers.IO) {
            api.getProfileInfo()
        }
}