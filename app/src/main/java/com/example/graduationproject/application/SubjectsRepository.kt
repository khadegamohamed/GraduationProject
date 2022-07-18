package com.example.graduationproject.application

import com.example.graduationproject.domain.dummyFirstTermSubjects
import com.example.graduationproject.domain.dummySecondTermSubjects
import com.example.graduationproject.domain.model.SubjectResponse
import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.subjects.model.SubjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class SubjectsRepository(private val api: ServiceAPI) {


    suspend fun getAllSubjectList()=
        withContext(Dispatchers.IO) {
            api.getAllSubjects()
        }




}