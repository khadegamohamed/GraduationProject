package com.example.graduationproject.presentation.home.view


import android.content.Context
import com.example.graduationproject.R.menu
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.collection.arrayMapOf
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.graduationproject.databinding.ListItemPostsBinding
import com.example.graduationproject.presentation.home.model.PostsPojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.R





class PostsAdepter(var arrayofpost:ArrayList<PostsPojo>, var context:Context, var postsListener: PostsListener,var view: View):RecyclerView.Adapter<PostsAdepter.ViewHolder>(){
private var viewModel = HomeViewModel(context)

    //viewholder
    class ViewHolder(val itembinding:ListItemPostsBinding):RecyclerView.ViewHolder(itembinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdepter.ViewHolder {
   return ViewHolder(ListItemPostsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }
//3
    override fun onBindViewHolder(holder: PostsAdepter.ViewHolder, position: Int) {
    var arrayOfMedia :ArrayList<String> = ArrayList()
    var post = arrayofpost.get(position)

   if(post.image!=null){
       Glide.with(context).load(post.image).into(holder.itembinding.ImagePost)
    }else{
        holder.itembinding.ImagePost.visibility =View.GONE
   }
         holder.itembinding.profileName.setText(post.profile_name)
        Glide.with(context).load(post.profile_photo).into(holder.itembinding.profileImage)
         holder.itembinding.creatAt.setText(post.createdAt)
         holder.itembinding.tvPost.setText(post.caption)

if(post.likesNum!=0) {
    holder.itembinding.numberOfLikes.setText(post.likesNum.toString())
    holder.itembinding.tvLike.setText(post.likesNum.toString())
}else{
    holder.itembinding.numberOfLikes.setText("")
    holder.itembinding.tvLike.setText("")
}
    if (post.comments.size!=0) {
        holder.itembinding.numberOfComment.setText(post.comments.size.toString())
    }else{
        holder.itembinding.numberOfComment.setText("")
    }
    //Like
    holder.itembinding.cardlike.setOnClickListener {
        postsListener.onClickLike(post._id,position)
    }
   holder.itembinding.tvLike.setOnClickListener {
        postsListener.onClickLike(post._id,position)
    }
    //numberOfLikes
    holder.itembinding.numberOfLikes.setOnClickListener {
        postsListener.onClickNumberOfLikes(post.likes)
    }
  //Image
 holder.itembinding.ImagePost.setOnClickListener {
     postsListener.onClickImages(post.image)
 }
    //DeletAndEdit
    holder.itembinding.deleteAndEdit.setOnClickListener {
        postsListener.onClickDeletOrEdit(context,view,post._id)
    }
    //Comments
    holder.itembinding.cardcomment.setOnClickListener {
        postsListener.onClickComment(post.comments,post._id)
    }

    }
//4
override fun getItemCount(): Int {
  return arrayofpost.size
    }
}