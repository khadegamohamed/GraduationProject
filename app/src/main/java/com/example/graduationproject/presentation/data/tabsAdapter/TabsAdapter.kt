package com.example.graduationproject.presentation.data.tabsAdapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduationproject.presentation.calender.CalenderFragment
import com.example.graduationproject.presentation.studying.StudyingFragment

 class TabsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,
    var totalTabs: Int) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = totalTabs

    override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    CalenderFragment()
                }
                1 -> {
                    StudyingFragment()
                }
                else -> CalenderFragment()


        }
    }


}
