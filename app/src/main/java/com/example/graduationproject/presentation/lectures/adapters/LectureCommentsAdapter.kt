package com.example.graduationproject.presentation.lectures.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationproject.R
import com.example.graduationproject.domain.model.LectureCommentResponseItem
import com.example.graduationproject.presentation.lectures.model.LectureCommentModel
import java.lang.Exception
import java.text.SimpleDateFormat

class LectureCommentsAdapter( private val context: Context,
    private val comments:List<LectureCommentResponseItem>):
    RecyclerView.Adapter<LectureCommentsAdapter.HolderComment>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderComment =
        HolderComment(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_lecture_comment, parent,false))

    override fun onBindViewHolder(holder: HolderComment, position: Int) {
      holder.bind(comments[position])
    }

    override fun getItemCount(): Int = comments.size

    inner class HolderComment(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var profileIV : ImageView = itemView.findViewById(R.id.profileIV)
        var nameTV: TextView = itemView.findViewById(R.id.UsernameTV)
        var dateTV: TextView = itemView.findViewById(R.id.dateTV)
        var commentTV: TextView = itemView.findViewById(R.id.commentTV)

        fun bind(commentModel: LectureCommentResponseItem) {
          val id = commentModel._id

            val published = commentModel.time
            val profileIV = commentModel.profile_photo


          // set data to views
            nameTV.text = commentModel.profile_name
            dateTV.text = formatDate(published)
            commentTV.text =commentModel.caption

            try {
                Glide.with(context)
                    .load(profileIV)
                    .placeholder(R.drawable.ic_person)
                    .into(this.profileIV)
            }
            catch (e:Exception){
             e.printStackTrace()
            }

        }

    }

    private fun formatDate(published:String) : String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateFormat2 = SimpleDateFormat("dd/MM/yyyy k:mm a")
        var formattedDate = ""
        try {
            val date = dateFormat.parse(published)
            formattedDate = dateFormat2.format(date)

        }catch (e:Exception){
            formattedDate = published
            e.printStackTrace()
        }
        return formattedDate
    }
}