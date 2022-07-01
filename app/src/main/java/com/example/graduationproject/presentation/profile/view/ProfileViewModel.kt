package com.example.graduationproject.presentation.profile.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.ProfileRepository
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.domain.networking.ServiceAPI
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel():ViewModel() {

    var profileResponse: MutableLiveData<Response<ProfileResponse>> = MutableLiveData()
    var serviceInst = RetrofitBuilder.getRetrofitClientInstance()
                        .create(ServiceAPI::class.java)
    private val profileRepo = ProfileRepository(serviceInst)

    fun getProfileInfo(){
        viewModelScope.launch {
            var result = profileRepo.getProfileInfo()
            profileResponse.value = result
        }
    }
}