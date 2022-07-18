package com.example.graduationproject.presentation.lectures.view

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationproject.application.LecturesRepository
import com.example.graduationproject.domain.model.Lecture
import com.example.graduationproject.domain.model.UploadLectureResponse
import com.example.graduationproject.domain.networking.RetrofitBuilder
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class LecturesViewModel(context: Context):ViewModel(){
    private val serviceInst = RetrofitBuilder.getAPIService(context)
    private val lectureRepo = LecturesRepository(serviceInst)
    var lecturesResponse: MutableLiveData<List<Lecture>> = MutableLiveData()
    var uploadLecturesResponse: MutableLiveData<Response<UploadLectureResponse>>
                                                       = MutableLiveData()

    suspend fun getAllLectures(id:String) {
        var response = lectureRepo.getLectures(id)
        Log.d("lectures: ", response.body()!!.lectures.toString())
        if(response.isSuccessful){
            if(response.body() != null){
                lecturesResponse.value = response.body()!!.lectures

            }

        }else{

            Log.d("lectures: ",response.message())
        }

    }

   suspend fun uploadLecture(file: MultipartBody.Part){
       var response = lectureRepo.uploadLecture(file)
       uploadLecturesResponse.value = response
   }

    fun addLecture(pdfName: String,fileName: File?) =
                            lectureRepo.addLectures(pdfName,fileName)
}