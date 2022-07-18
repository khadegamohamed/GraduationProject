package com.example.graduationproject.presentation.data.tabsAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduationproject.presentation.calender.view.CalenderFragment
import com.example.graduationproject.presentation.studying.StudyingFragment

 class TabsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,
    var totalTabs: Int) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = totalTabs

    override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    StudyingFragment()
                }
                1 -> {
                    CalenderFragment()
                }
                else -> StudyingFragment()


        }
    }


}
