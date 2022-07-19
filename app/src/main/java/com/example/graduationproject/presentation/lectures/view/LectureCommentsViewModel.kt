package com.example.graduationproject.presentation.lectures.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.LectureCommentRepository
import com.example.graduationproject.domain.model.LectureCommentResponseItem
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.presentation.lectures.model.LectureComment
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class LectureCommentsViewModel(context: Context): ViewModel() {
    private val serviceInst = RetrofitBuilder.getAPIService(context)
    private val lectureCommentRepo = LectureCommentRepository (serviceInst)
     var lectureCommentResponse: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
     var lectureAllCommentsResponse: MutableLiveData<Response<List<LectureCommentResponseItem>>>
                                                       = MutableLiveData()

    suspend fun addComment(id:String,comment: LectureComment){
        viewModelScope.launch {
            val result = lectureCommentRepo.addComment(id, comment)
            lectureCommentResponse.value = result
        }
    }

    suspend fun getAllComments(lectureId:String){
        viewModelScope.launch {
            val result = lectureCommentRepo.getAllComments(lectureId)
            lectureAllCommentsResponse.value = result
        }
    }

}