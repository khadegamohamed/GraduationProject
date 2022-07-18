package com.example.graduationproject.presentation.home.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.HomeRepository
import com.example.graduationproject.domain.model.ProfileResponse
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.presentation.home.model.LikePojo
import com.example.graduationproject.presentation.home.model.PostsPojo
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response


class HomeViewModel(context: Context) :ViewModel(){

   var mutablePostsResponse:MutableLiveData<Response<List<PostsPojo>>> = MutableLiveData()
    var mutableDeleteResponse: MutableLiveData<Response<String>> = MutableLiveData()
    var profileResponse: MutableLiveData<Response<ProfileResponse>> = MutableLiveData()
    var mutableAddLikeResponse: MutableLiveData<Response<LikePojo>> = MutableLiveData()


    var serviceInst = RetrofitBuilder.getAPIService(context)
    private var homeRepo =HomeRepository(serviceInst)

        fun getAllPosts() {
          viewModelScope.launch {
               homeRepo.getAllPosts().catch {e->
                   Log.d("HomeViewModel", "getAllPosts: ${e.message}")
               }.collect {response ->
                   mutablePostsResponse.value=response
               }

          }
        }


    fun addLike(id:String) {
        viewModelScope.launch {
          var addLike=   homeRepo.addLike(id)
            mutableAddLikeResponse.value = addLike
        }
    }

    fun deletePost(id:String) {
        viewModelScope.launch {
            var deletePost = homeRepo.deletePost(id)
            mutableDeleteResponse.value = deletePost
        }
    }

    fun getProfileInfo(){
        viewModelScope.launch {
            var result = homeRepo.getProfileInfo()
            profileResponse.value = result
        }
    }


}