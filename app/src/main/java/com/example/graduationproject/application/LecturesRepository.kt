package com.example.graduationproject.application

import com.example.graduationproject.domain.dummyLectures
import com.example.graduationproject.domain.networking.ServiceAPI
import com.example.graduationproject.presentation.lectures.model.LectureModel
import kotlinx.coroutines.flow.flow

import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

import java.io.File

class LecturesRepository(private val api: ServiceAPI) {
    fun getDummyLectures() = flow {
        emit(dummyLectures)
    }



    fun addLectures(pdfName: String, fileName: File?) =
        dummyLectures.add(LectureModel("0", pdfName, fileName))


    suspend fun getLectures(id: String) =
        withContext(Dispatchers.IO) {
            api.getLectures(id)
        }


    suspend fun uploadLecture(file: MultipartBody.Part) =
        withContext(Dispatchers.IO) {
            api.uploadPdfFile(file)
        }

}