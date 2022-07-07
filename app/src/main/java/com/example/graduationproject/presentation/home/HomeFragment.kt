package com.example.graduationproject.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val arrayofstories:ArrayList<String> = ArrayList()
        arrayofstories.add("hello")
        arrayofstories.add("hello")
        arrayofstories.add("hello")
        arrayofstories.add("hello")
        arrayofstories.add("hello")
        arrayofstories.add("hello")
        arrayofstories.add("hello")
        arrayofstories.add("hello")
        arrayofstories.add("hello")
     val arrayofposts:ArrayList<DataclassPosts> = ArrayList()
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        arrayofposts.add(DataclassPosts(R.drawable.ic_person_24,"hello","helloworld"))
        val adepter:PostsAdepter= PostsAdepter(arrayofposts,arrayofstories,requireContext())
       val lm:RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerofposts.layoutManager = lm
        binding.recyclerofposts.adapter = adepter
        binding.recyclerofposts.setHasFixedSize(true)


        binding.personFab.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragment2ToProfileFragment()
            )
        }
    }

}