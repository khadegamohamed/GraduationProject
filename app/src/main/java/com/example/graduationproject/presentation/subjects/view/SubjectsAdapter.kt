package com.example.graduationproject.presentation.subjects.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationproject.Navgraph2Directions
import com.example.graduationproject.R
import com.example.graduationproject.presentation.subjects.model.SubjectModel


class SubjectsAdapter(private val subjectsList: List<SubjectModel>) :
    RecyclerView.Adapter<SubjectsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.subjects_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(subjectsList[position])


    override fun getItemCount(): Int = subjectsList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var subjectName: TextView = itemView.findViewById(R.id.subject_name)
        var subjectImage: ImageView = itemView.findViewById(R.id.subject_image)


        fun bind(subjectModel: SubjectModel) {
            subjectName.text = subjectModel.subjectName

            Glide.with(itemView)
                .load(subjectModel.imageUrl)
                .centerCrop()
                .into(subjectImage)

            itemView.setOnClickListener {
             // navigate to lectures of each subject
               val navController = Navigation.findNavController(itemView)
               navController.navigate(Navgraph2Directions
                           .actionNavigateToLecturesFragment(subjectModel.id))

            }

        }


    }


}
