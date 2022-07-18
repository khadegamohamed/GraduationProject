package com.example.graduationproject.presentation.home.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.CommentsRepository
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.presentation.home.model.Comment
import com.example.graduationproject.presentation.home.model.CommentPojo
import com.example.graduationproject.presentation.home.model.RequestAddComment
import kotlinx.coroutines.launch
import retrofit2.Response

class CommentsViewModel(context: Context):ViewModel() {
    var mutableCommentResponse: MutableLiveData<Response<List<Comment>>> = MutableLiveData()
    var serviceInst = RetrofitBuilder.getAPIService(context)
    private var commentRepo = CommentsRepository(serviceInst)

    fun addComment(requestOfComment: RequestAddComment,id:String) {
        viewModelScope.launch {
              var respones = commentRepo.addComment(requestOfComment,id)
            mutableCommentResponse.value= respones
        }
    }

}