package com.example.graduationproject.application

import com.example.graduationproject.domain.dummyFirstTermSubjects
import com.example.graduationproject.domain.dummySecondTermSubjects
import com.example.graduationproject.presentation.subjects.model.SubjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class SubjectsRepository {

    fun getFirstTermSubjectList() = flow{
        emit(dummyFirstTermSubjects)
    }
   fun getFirstTermSubjects() = dummyFirstTermSubjects

    fun getSecondTermSubjectList() = flow{
        emit(dummySecondTermSubjects)
    }


   /** suspend fun getFirstTermSubjects =
        withContext(Dispatchers.IO){
            api.getFierstTermSubjects()
        }

   suspend fun getSecondTermSubjects =
   withContext(Dispatchers.IO){
   api.getSecondTermSubjects()
      }
   */


}