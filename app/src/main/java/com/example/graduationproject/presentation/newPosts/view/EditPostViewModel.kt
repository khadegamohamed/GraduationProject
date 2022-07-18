package com.example.graduationproject.presentation.newPosts.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.EditPostRepository
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.presentation.newPosts.model.RequestEditPost
import kotlinx.coroutines.launch
import retrofit2.Response

class EditPostViewModel(context: Context):ViewModel() {
    var mutableEditResponse: MutableLiveData<Response<String>> = MutableLiveData()
    var serviceInst = RetrofitBuilder.getAPIService(context)
    private var editRepo = EditPostRepository(serviceInst)

    fun editPost(requestEditPost: RequestEditPost ,id:String) {
        viewModelScope.launch {
           var response  =  editRepo.editPost(requestEditPost,id)
            mutableEditResponse.value = response
        }
    }

}