package com.example.graduationproject.application

import com.example.graduationproject.domain.dummyFirstTermSubjects
import com.example.graduationproject.domain.dummySecondTermSubjects
import com.example.graduationproject.presentation.subjects.model.SubjectModel
import kotlinx.coroutines.flow.flow

class SubjectsRepository {

    fun getFirstTermSubjectList() = flow{
        emit(dummyFirstTermSubjects)
    }


    fun getSecondTermSubjectList() = flow{
        emit(dummySecondTermSubjects)
    }
}