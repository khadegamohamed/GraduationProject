package com.example.graduationproject.presentation.data.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationproject.databinding.FragmentDataBinding
import com.example.graduationproject.presentation.data.tabsAdapter.TabsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DataFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private lateinit var adapter: TabsAdapter
 private val tabsArray = arrayListOf("Calender","Studying")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()

    }

    private fun setupTabs() {
       binding.tabLayout.addTab(binding.tabLayout.newTab())
       binding.tabLayout.addTab(binding.tabLayout.newTab())
        adapter = TabsAdapter(
            requireActivity().supportFragmentManager, lifecycle,
            binding.tabLayout.tabCount
        )
         binding.viewPager.adapter = adapter

         TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab,position ->
             tab.text =tabsArray[position] }.attach()

    }

}