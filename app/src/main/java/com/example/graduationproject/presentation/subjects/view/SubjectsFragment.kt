package com.example.graduationproject.presentation.subjects.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentSubjectsBinding
import com.example.graduationproject.presentation.subjects.model.SubjectModel
//import kotlinx.coroutines.flow.collect

class SubjectsFragment : Fragment() {
    lateinit var binding: FragmentSubjectsBinding
    lateinit var subjectsViewModel: SubjectsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentSubjectsBinding.inflate(inflater, container, false)
        subjectsViewModel = SubjectsViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val termNumber = SubjectsFragmentArgs.fromBundle(requireArguments()).termNumber
       /* viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            when (termNumber) {
                1 -> {
                  subjectsViewModel.getFirstTermSubjectList().collect{
                      binding.subjectsRv.adapter = SubjectsAdapter(it)
                  }

                }

                2 -> {
                    subjectsViewModel.getSecondTermSubjectList().collect {
                        binding.subjectsRv.adapter = SubjectsAdapter(it)
                    }
                }
            }
        }*/

    }


}