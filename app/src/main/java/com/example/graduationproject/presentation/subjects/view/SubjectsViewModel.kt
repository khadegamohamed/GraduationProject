package com.example.graduationproject.presentation.subjects.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.SubjectsRepository
import com.example.graduationproject.domain.model.SubjectResponse
import com.example.graduationproject.domain.networking.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.Response

class SubjectsViewModel(context: Context): ViewModel() {
   private val serviceInst = RetrofitBuilder.getAPIService(context)
   private val subjectsRepo = SubjectsRepository(serviceInst)
   var firstSubjectsResponse:MutableLiveData<List<SubjectResponse> > = MutableLiveData()
   var secondTermSubjectsResponse:MutableLiveData<List<SubjectResponse>> = MutableLiveData()



   fun getFirstTermSubjects() = viewModelScope.launch {
      var result = subjectsRepo.getAllSubjectList()
       if(result.isSuccessful){
          if(result.body() != null){
             firstSubjectsResponse.value = result.body()!!.take(3)
          }
       }


   }

   fun getSecondTermSubjects() = viewModelScope.launch {
      var result = subjectsRepo.getAllSubjectList()
      if(result.isSuccessful){
         if(result.body() != null){
            secondTermSubjectsResponse.value = result.body()!!.takeLast(3)
         }
      }


   }

}