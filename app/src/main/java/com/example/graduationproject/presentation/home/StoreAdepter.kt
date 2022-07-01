package com.example.graduationproject.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.databinding.ListItemStoriesBinding

class StoreAdepter(val arrayofstories:ArrayList<String>):RecyclerView.Adapter<StoreAdepter.Viewholder>() {
   val arrayOfstroies:ArrayList<String>
   init {
       arrayOfstroies = arrayofstories
   }
    class Viewholder(val binding: ListItemStoriesBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
      return Viewholder(ListItemStoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
      var store = arrayOfstroies.get(position)
        holder.binding.texttest.setText(store)
    }

    override fun getItemCount(): Int {
   return  arrayOfstroies.size
    }
}