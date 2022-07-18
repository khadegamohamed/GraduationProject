package com.example.graduationproject.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.databinding.FragmentWhoislikeBinding
import com.example.graduationproject.presentation.home.model.Like


class WhoIsLike:Fragment() {
    private lateinit var binding:FragmentWhoislikeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhoislikeBinding.inflate(inflater,container,false)
      return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerOfImage=binding.listOfLikes
        var listOfLikes= WhoIsLikeArgs.fromBundle(requireArguments()).listOfLikes
        var list:List<Like> = listOfLikes.listOfLikes
        var likesAdepter =LikesAdepter(list,requireContext())
        val lm: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        recyclerOfImage.layoutManager = lm
        recyclerOfImage.adapter =likesAdepter
        recyclerOfImage.setHasFixedSize(true)
    }




}