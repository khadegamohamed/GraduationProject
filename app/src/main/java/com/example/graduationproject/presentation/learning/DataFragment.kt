package com.example.graduationproject.presentation.learning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentDataBinding
import com.example.graduationproject.presentation.learning.tabaAdapter.MyAdapter
import com.google.android.material.tabs.TabLayout


class DataFragment : Fragment() {
  private lateinit var binding: FragmentDataBinding
  private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentDataBinding.inflate(inflater,container,false)

         setupTabs()


        return binding.root
    }

    private fun setupTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Calender"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Studying"))

        adapter = MyAdapter(requireContext(),
            requireActivity()!!.supportFragmentManager,
            binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

}