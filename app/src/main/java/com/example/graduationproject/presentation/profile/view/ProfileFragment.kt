package com.example.graduationproject.presentation.profile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var sharedPrefManager: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ProfileViewModel(this.requireContext())
        sharedPrefManager = SharedPreferenceManager(this.requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // gat token from sharedPreferences
        profileViewModel.getProfileInfo()

        profileViewModel.profileResponse.observe(viewLifecycleOwner,
            Observer { response ->
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result != null) {
                        binding.profileNameTV.text = result.profile_name
                        binding.userNameTV.text = result.user_name
                        binding.userRoleTV.text = result.role
                        if (result.profile_photo != null) {
                            Glide.with(view)
                                .load(result.profile_photo)
                                .into(binding.profileImg)
                        }
                        Toast.makeText(activity,
                            response.body().toString(), Toast.LENGTH_SHORT).show()

                        Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity,
                        response.message().toString(), Toast.LENGTH_SHORT).show()
                    Log.d("ProfileResponse: ", response.message().toString())
                }
            })

    }

}