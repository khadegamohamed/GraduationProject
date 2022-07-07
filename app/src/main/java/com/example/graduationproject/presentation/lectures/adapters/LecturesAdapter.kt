package com.example.graduationproject.presentation.lectures.view

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.R
import com.example.graduationproject.presentation.lectures.model.LectureModel
import com.github.barteksc.pdfviewer.PDFView
import com.google.android.material.internal.ContextUtils.getActivity
import java.io.File
import java.security.AccessController.getContext

class LecturesAdapter(private val lectures: List<LectureModel>) :
    RecyclerView.Adapter<LecturesAdapter.ViewHolder>() {
    private lateinit var  context:Context
    var onListItemClick: OnListItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.lectures_list_item,
                    parent, false
                )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lectures[position])
    }

    override fun getItemCount(): Int = lectures.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lectureName: TextView = itemView.findViewById(R.id.lecture_name)
        var pdfView: PDFView = itemView.findViewById(R.id.pdfView)
        fun bind(lectureModel: LectureModel) {
            lectureName.text = lectureModel.lectureName
            //  lectureModel.lectureName?.let { showPdfFromAssets(it) }

            itemView.setOnClickListener {
//                val navController = Navigation.findNavController(itemView)
//                navController.navigate(
//                    Navgraph2Directions
//                        .actionNavigateToLectureDisplayFragment(lectureModel.lectureName))
                onListItemClick?.onItemClick()

            }


        }




    }

    interface OnListItemClick{
        fun onItemClick()
    }


}