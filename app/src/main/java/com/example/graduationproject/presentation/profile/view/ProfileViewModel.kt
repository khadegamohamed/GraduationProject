package com.example.graduationproject.presentation.profile.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.ProfileRepository
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.presentation.profile.model.Profile
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class ProfileViewModel(context: Context):ViewModel() {

    var profileResponse: MutableLiveData<Response<ProfileResponse>> = MutableLiveData()
    var updateNameResponse:MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    var updateImageResponse:MutableLiveData<Response<ResponseBody>> = MutableLiveData()

    var serviceInst = RetrofitBuilder.getAPIService(context)
    private val profileRepo = ProfileRepository(serviceInst)

    fun getProfileInfo(){
        viewModelScope.launch {
            var result = profileRepo.getProfileInfo()
            profileResponse.value = result
        }
    }
    fun updateProfileName(id:String,profileName:Profile){
        viewModelScope.launch {
          var result =  profileRepo.updateProfileName(id,profileName )
          updateNameResponse.value = result
        }
    }
   fun updateProfileImage(id:String,imageFile: MultipartBody.Part){
       viewModelScope.launch {
           var result = profileRepo.updateProfileImage(id,imageFile)
           updateImageResponse.value = result
       }
   }
}