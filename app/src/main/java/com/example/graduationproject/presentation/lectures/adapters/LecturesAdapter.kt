package com.example.graduationproject.presentation.lectures.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.NavagationDirections
import com.example.graduationproject.R
import com.example.graduationproject.domain.model.Lecture
import com.github.barteksc.pdfviewer.PDFView

class LecturesAdapter(private val lectures: List<Lecture>) :
    RecyclerView.Adapter<LecturesAdapter.ViewHolder>() {
    private lateinit var  context:Context
    var onListItemClick: OnListItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.lectures_list_item,
                    parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lectures[position])
    }

    override fun getItemCount(): Int = lectures.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lectureName: TextView = itemView.findViewById(R.id.lecture_name)
        var pdfView: PDFView = itemView.findViewById(R.id.pdfView)
        var CommentsimageView: ImageView = itemView.findViewById(R.id.comments_icon)
        fun bind(lecture: Lecture) {
            lectureName.text = lecture.fileName

           // by clicking on each lecture you can go to its discussion screen
            CommentsimageView.setOnClickListener {
                val navController = Navigation.findNavController(itemView)
                navController.navigate(NavagationDirections
                    .actionLecturesFragmentToLectureCommentsFragment(lecture._id))
            }

            itemView.setOnClickListener {
                val navController = Navigation.findNavController(itemView)
                navController.navigate(NavagationDirections
                    .actionNavigateToLectureDisplayFragment(lecture)
                )

               // onListItemClick?.onItemClick()

            }


        }




    }

    interface OnListItemClick{
        fun onItemClick()
    }


}