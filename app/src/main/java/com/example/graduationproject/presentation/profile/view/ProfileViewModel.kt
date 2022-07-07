package com.example.graduationproject.presentation.profile.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.ProfileRepository
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.domain.networking.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel(context: Context):ViewModel() {

    var profileResponse: MutableLiveData<Response<ProfileResponse>> = MutableLiveData()
    var serviceInst = RetrofitBuilder.getAPIService(context)
    private val profileRepo = ProfileRepository(serviceInst)

    fun getProfileInfo(){
        viewModelScope.launch {
            var result = profileRepo.getProfileInfo()
            profileResponse.value = result
        }
    }
}