package com.example.graduationproject.presentation.subjects.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.example.graduationproject.Navgraph2Directions
import com.example.graduationproject.R
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.domain.model.SubjectResponse


class SubjectsAdapter(private val subjectsList: List<SubjectResponse>) :
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
        var sharedPref = SharedPreferenceManager(itemView.context)
        var subjectName: TextView = itemView.findViewById(R.id.subject_name)


        fun bind(subjectResponse: SubjectResponse) {
            subjectName.text = subjectResponse.subject_name



            itemView.setOnClickListener {
             // navigate to lectures of each subject if the user is a student,
                // if he is doctor go to PdfFragment
                val role = sharedPref.getUserRole()
               val navController = Navigation.findNavController(itemView)
               // if(role == "Student")
                 navController.navigate(SubjectsFragmentDirections
                     .actionNavigateToLecturesFragment(subjectResponse._id))

               //else if (role == "Doctor")
                  // navController.navigate(SubjectsFragmentDirections
                   //                   .actionSubjectsFragmentToPdfFragment())

            }

        }


    }


}
