package com.example.graduationproject.presentation.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.R
import com.example.graduationproject.databinding.DialogApoutAppBinding
import com.example.graduationproject.databinding.FragmentSettingsBinding
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SettingsFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentSettingsBinding
    lateinit var sharedPref: SharedPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        sharedPref = SharedPreferenceManager(this.requireContext())
        // setup app mode
         binding.switchMode.setOnClickListener {
               if(!binding.switchMode.isChecked){
                   AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                  // binding.languageImg.setImageResource(R.drawable.ic_dark_mode_24)
                  // binding.languageTV.text="Dark"
               }

               if(binding.switchMode.isChecked){
                   AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
               }

             binding.languageLayout.setOnClickListener {
                // requireActivity().updateLocale()
             }

             changeLanguage()

         }
        // handle info
        binding.infoTV.setOnClickListener { initAppInfoDialog() }
        // handle logout, by clearing user data and then entering him to login again
        binding.logoutLayout.setOnClickListener {
            //sharedPref.signOut()
            sharedPref.saveToken("")
            findNavController().navigate(SettingsFragmentDirections
                                 .actionSettingsFragment2ToLoginFragment())
        }


        return binding.root
    }

    private fun changeLanguage() {
        //

    }

    @SuppressLint("SetTextI18n")
    private fun initAppInfoDialog() {
        val aboutAppBinding = DialogApoutAppBinding.inflate(LayoutInflater.from(this.requireContext()))
        // setup alert dialog
        val builder = AlertDialog.Builder(this.requireContext(), R.style.CustomDialog)
        builder.setView(aboutAppBinding.root)
        // create and show alert dialog
        val alertDialog = builder.create()
        alertDialog.show()
        aboutAppBinding.infoShowTV.text =
            "This is an android app for a college, " +
                    "can help doctors and student by providing one thing (app)" +
                    " that contains all subjects with all lectures, it also enable students" +
                    " to ask any question on any lecture and the doctor or " +
                    "any of their friends can see and answer " +
                    "a bout the question, it also can contain important info like exam table," +
                    "task deadline, change in lecture time these things can provided by calender part," +
                    "we also can provide a part for services like cafeteria in our college," +
                    "and enable students to order what ever they need from it, wherever they were,...  "
    }

}