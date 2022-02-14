package com.example.graduationproject.presentation.learning.tabaAdapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.graduationproject.presentation.calender.CalenderFragment
import com.example.graduationproject.presentation.studying.StudyingFragment

internal class MyAdapter(var context: Context,fm:FragmentManager,var totalTabs:Int):
                                              FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
      return when(position){
          0 ->  CalenderFragment()
          1 -> StudyingFragment()
          else ->  getItem(position)
      }
    }
    override fun getCount(): Int {
        return totalTabs
    }

}