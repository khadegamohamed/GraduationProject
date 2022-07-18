package com.example.graduationproject.domain

import android.content.Context
import com.example.graduationproject.presentation.lectures.model.LectureModel
import com.example.graduationproject.presentation.subjects.model.SubjectModel
import java.io.File

val dummyFirstTermSubjects = listOf(
    SubjectModel(0,"Compiler","https://picsum.photos/500/300?random=1"),
    SubjectModel(1,"NetWork","https://picsum.photos/500/300?random=2"),
    SubjectModel(2,"Computer Architecture","https://picsum.photos/500/300?random=3"),
    SubjectModel(3,"Computer Aided Design","https://picsum.photos/500/300?random=4"),
    SubjectModel(4,"Flutter","https://picsum.photos/500/300?random=5"),
    SubjectModel(5,"Internet Of Things","https://picsum.photos/500/300?random=6"),

 )


 val dummySecondTermSubjects = listOf(
    SubjectModel(0,"Embedded Systems","https://picsum.photos/500/300?random=1"),
    SubjectModel(1,"Interfaces","https://picsum.photos/500/300?random=2"),
    SubjectModel(2,"Artificial Intelligence","https://picsum.photos/500/300?random=3"),
    SubjectModel(3,"Signals","https://picsum.photos/500/300?random=4"),
    SubjectModel(4,"Data Since","https://picsum.photos/500/300?random=5"),
    SubjectModel(5,"Software Engineering","https://picsum.photos/500/300?random=6"),
 )



val file1 = File("src/main/assets/2-Intelligent_Agents.pdf")
val file2 = File("src/main/assets/Chapter1_SW.pdf")
    val dummyLectures = mutableListOf(
        LectureModel("0", "2-Intelligent_Agents.pdf",file1),
        LectureModel("1", "Chapter1_SW.pdf", file2),
    )

