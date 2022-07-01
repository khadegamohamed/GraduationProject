package com.example.graduationproject.presentation.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.databinding.ListItemPostsBinding
import com.example.graduationproject.databinding.ListItemRecyclerstoriesBinding

class PostsAdepter(arrayofpost:ArrayList<DataclassPosts>,arrayofstore:ArrayList<String>,context:Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
  //constractor
    val arrayofstories:ArrayList<String>
    val arrayofposts:ArrayList<DataclassPosts>
    val context:Context
    init {
        arrayofposts = arrayofpost
        arrayofstories = arrayofstore
        this.context = context
    }

    //viewholder
    class ViewHolder(val itembinding:ListItemPostsBinding):RecyclerView.ViewHolder(itembinding.root)
    class ViewHolder2(val itembinding:ListItemRecyclerstoriesBinding):RecyclerView.ViewHolder(itembinding.root)

//functionofAdepter
    //1
    override fun getItemViewType(position: Int): Int {
        if(position == 0){
            return 0
        }else{
            return 1
        }

    }
//2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    if (viewType==0){
        return ViewHolder2(ListItemRecyclerstoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }else{
        return ViewHolder(ListItemPostsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    }
//3
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
     if(holder.itemViewType ==0){
       val recyclerofstories=   ( holder as ViewHolder2).itembinding.recyclerofstorie
         val layoutmanger:RecyclerView.LayoutManager = LinearLayoutManager(context,
             LinearLayoutManager.HORIZONTAL,false)
         val adepterofstories:StoreAdepter = StoreAdepter(arrayofstories)
         recyclerofstories.layoutManager  = layoutmanger
         recyclerofstories.adapter = adepterofstories
         recyclerofstories.setHasFixedSize(true)
     }else{
         var post = arrayofposts.get(position)
         (holder as ViewHolder).itembinding.nameofpost.setText(post.title)
         (holder as ViewHolder).itembinding.thepost.setText(post.thepost)
         (holder as ViewHolder).itembinding.imageofpost.setImageResource(post.image)
     }
    }
//4
override fun getItemCount(): Int {
  return arrayofposts.size;
    }
}