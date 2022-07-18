package com.example.graduationproject.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.databinding.FragmentCommentsBinding
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.presentation.home.model.Comment
import com.example.graduationproject.presentation.home.model.RequestAddComment

class CommentsFragment:Fragment() {
private lateinit var binding:FragmentCommentsBinding
private lateinit var commentsViewModel: CommentsViewModel
private lateinit var sharedPreference: SharedPreferenceManager
private lateinit var commentsAdepter:CommentsAdepter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentsBinding.inflate(inflater,container,false)
        commentsViewModel = CommentsViewModel(requireContext())
        sharedPreference  = SharedPreferenceManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerOfComments=binding.listofcomments
        var arrayOfComments= CommentsFragmentArgs.fromBundle(requireArguments()).listOfComments
        var id:String= arrayOfComments!!.idOfPost
        var listOfComments = arrayOfComments.listOfComments as ArrayList<Comment>
       commentsAdepter= CommentsAdepter(listOfComments,requireContext())
        val lm: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        recyclerOfComments.layoutManager = lm
        recyclerOfComments.adapter =commentsAdepter
        recyclerOfComments.setHasFixedSize(true)
        binding.sendComment.setOnClickListener {
            var caption = binding.addComment.text.toString()
            var token = sharedPreference.getToken()
            var requestOfComments = RequestAddComment(caption,token)
            commentsViewModel.addComment(requestOfComments,id)
      commentsViewModel.mutableCommentResponse.observe(viewLifecycleOwner,{
          if (it.isSuccessful){
              var newList = it.body() as ArrayList<Comment>
              commentsAdepter.setData(newList)
              Toast.makeText(requireContext(),"The Comment is recored", Toast.LENGTH_LONG).show()
          }else{
              Toast.makeText(requireContext(),"Error in Comment", Toast.LENGTH_LONG).show()
          }
      })
        }

    }

}