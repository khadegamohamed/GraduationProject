package com.example.graduationproject.application

import com.example.graduationproject.domain.dummyLectures
import com.example.graduationproject.presentation.lectures.model.LectureModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.File

class LecturesRepository {
    fun getDummyLectures() = flow {
        emit(dummyLectures)
    }

    fun addLectures(pdfName: String,fileName: File?) =
                dummyLectures.add(LectureModel(0,pdfName,fileName))

  /**      suspend fun uploadLectures(lectureModel: LectureModel) =
        withContext(Dispatchers.IO){
            api.uploadLectures(lectureModel)
        }

        suspend fun getLectures() =
            withContext(Dispatchers.IO){
                api.getLectures()
            }
     */

}