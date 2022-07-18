package com.example.graduationproject.presentation.calender.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.databinding.ListItemCommentBinding
import com.example.graduationproject.databinding.ListitemshowdateeventBinding
import com.example.graduationproject.presentation.calender.model.EventPojo
import com.example.graduationproject.presentation.home.view.CommentsAdepter

class AdepterEvents(var eventsList:List<EventPojo>):RecyclerView.Adapter<AdepterEvents.ViewHolder>() {

    class ViewHolder(val dateEventsBinding: ListitemshowdateeventBinding):RecyclerView.ViewHolder(dateEventsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AdepterEvents.ViewHolder(
           ListitemshowdateeventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var theEvent = eventsList[position]
        holder.dateEventsBinding.SubjectName.setText(theEvent.subject_name)
        holder.dateEventsBinding.fromDate.setText(theEvent.fromDate)
        holder.dateEventsBinding.fromTime.setText(theEvent.fromTime)
        holder.dateEventsBinding.toDate.setText(theEvent.toDate)
        holder.dateEventsBinding.toTime.setText(theEvent.toTime)
    }

    override fun getItemCount(): Int {
       return eventsList.size
    }


}