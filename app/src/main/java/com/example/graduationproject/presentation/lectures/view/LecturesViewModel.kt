package com.example.graduationproject.presentation.lectures.view

import androidx.lifecycle.ViewModel
import com.example.graduationproject.application.LecturesRepository
import java.io.File

class LecturesViewModel():ViewModel(){
    private val lectureRepo = LecturesRepository()

    fun getLectures() = lectureRepo.getDummyLectures()

    fun addLecture(pdfName: String,fileName: File?) =
                            lectureRepo.addLectures(pdfName,fileName)
}