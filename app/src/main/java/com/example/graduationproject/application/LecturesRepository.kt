package com.example.graduationproject.application

import com.example.graduationproject.domain.dummyLectures
import com.example.graduationproject.presentation.lectures.model.LectureModel
import kotlinx.coroutines.flow.flow
import java.io.File

class LecturesRepository {
    fun getDummyLectures() = flow {
        emit(dummyLectures)
    }

    fun addLectures(pdfName: String,fileName: File?) =
                dummyLectures.add(LectureModel(0,pdfName,fileName))
}