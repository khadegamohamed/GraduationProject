package com.example.graduationproject.presentation.profile.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.graduationproject.R
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var sharedPrefManager: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
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
                        // save user id
                        sharedPrefManager.saveUserId(result._id)
                        sharedPrefManager.saveProfileName(result.profile_name)

                        if (result.profile_photo != null) {
                            Glide.with(view)
                                .load(result.profile_photo)
                                .into(binding.profileImg)
                        }else{
                            binding.profileImg
                                .setImageResource(R.drawable.student_image)
                        }

                        Toast.makeText(activity,"success!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity,
                        response.message().toString(), Toast.LENGTH_SHORT).show()
                    Log.d("ProfileResponse: ", response.message().toString())
                }
            })

        binding.editProfileTV.setOnClickListener {
            // if user clicked on update btn go to EditProfileFragment
            findNavController().navigate(
                ProfileFragmentDirections
                    .actionProfileFragmentToEditProfileFragment2()
            )
        }

    }

}