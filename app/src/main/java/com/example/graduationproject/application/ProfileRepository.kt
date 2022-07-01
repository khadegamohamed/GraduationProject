package com.example.graduationproject.application

import androidx.lifecycle.MutableLiveData
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.domain.networking.ServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProfileRepository(private val api: ServiceAPI) {

    suspend fun getProfileInfo() =
        withContext(Dispatchers.IO) {
            api.getProfileInfo()
        }
}