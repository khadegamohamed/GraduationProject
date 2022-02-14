package com.example.graduationproject.presentation.studying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentStudyingBinding

class StudyingFragment : Fragment() {
 private lateinit var  binding : FragmentStudyingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudyingBinding.inflate(inflater,container,false)
        return binding.root
    }

}