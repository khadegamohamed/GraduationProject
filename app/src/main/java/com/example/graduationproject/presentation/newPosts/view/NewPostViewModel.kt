package com.example.graduationproject.presentation.newPosts.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.NewPostRepository
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.home.model.PostsPojo
import com.example.graduationproject.presentation.newPosts.model.RequestNewPost
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class NewPostViewModel(var context:Context):ViewModel() {

    var mutableNewPostResponse: MutableLiveData<Response<PostsPojo>> = MutableLiveData()
    var mutableNewPostWithMediaResponse: MutableLiveData<Response<PostsPojo>> = MutableLiveData()
    var serviceInst = RetrofitBuilder.getAPIService(context)

    private var newPostRepo = NewPostRepository(serviceInst)

    fun addNewPost(post:RequestNewPost) {
        viewModelScope.launch {
            var response =  newPostRepo.addNewPost(post)
       mutableNewPostResponse.value = response
        }
    }

    fun addNewPostWithMedia(caption: String, file: MultipartBody.Part) {
        viewModelScope.launch {
            var response =  newPostRepo.addNewPostWithMedia(caption,file)
            mutableNewPostResponse.value = response
        }
    }


}