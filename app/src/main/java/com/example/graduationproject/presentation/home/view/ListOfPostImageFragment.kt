package com.example.graduationproject.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.graduationproject.databinding.FragmentlistofpostimagesBinding


class ListOfPostImageFragment:Fragment() {
private  lateinit var binding:FragmentlistofpostimagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentlistofpostimagesBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var theImage = ListOfPostImageFragmentArgs.fromBundle(requireArguments()).image
        Glide.with(view)
            .load(theImage)
            .into(binding.TheImageInPost)

    }
}