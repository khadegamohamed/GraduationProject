package com.example.graduationproject.presentation.subjects.view

import androidx.lifecycle.ViewModel
import com.example.graduationproject.application.SubjectsRepository

class SubjectsViewModel(): ViewModel() {
   private val subjectsRepo = SubjectsRepository()

   fun getFirstTermSubjectList() = subjectsRepo.getFirstTermSubjectList()

   fun getSecondTermSubjectList() = subjectsRepo.getSecondTermSubjectList()

}