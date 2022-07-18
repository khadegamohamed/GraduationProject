package com.example.graduationproject.presentation.calender.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject.databinding.FragmentCalenderBinding
import com.example.graduationproject.databinding.FragmentShoweventsBinding
import com.example.graduationproject.presentation.calender.model.DateEventRequest
import com.example.graduationproject.presentation.calender.model.EventRequest
import com.example.graduationproject.presentation.home.view.CommentsAdepter

class ShowEvents :Fragment(){
    private lateinit var binding: FragmentShoweventsBinding
    private lateinit var  calenderViewModel: CalenderViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoweventsBinding.inflate(inflater, container, false)
        calenderViewModel = CalenderViewModel(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var date = ShowEventsArgs.fromBundle(requireArguments()).theDate
        var dateeventRequest= DateEventRequest(date)
        calenderViewModel.getDateEvents(dateeventRequest)
        calenderViewModel.mutableDateEventsResponse.observe(viewLifecycleOwner,{
            if (it.isSuccessful){
                var eventsAdepter= AdepterEvents(it.body()!!)
                Log.i("SHOWEVENTS", "${it.body()}")
                val lm: RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
                binding.listOfEvents.layoutManager= lm
                binding.listOfEvents .adapter =eventsAdepter
                binding.listOfEvents.setHasFixedSize(true)
            }else{
                Log.i("SHOWEVENTS", "${it.body()}")
            }
        })

    }



}