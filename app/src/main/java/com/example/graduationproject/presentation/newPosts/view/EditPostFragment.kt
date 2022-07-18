package com.example.graduationproject.presentation.newPosts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.graduationproject.databinding.FragmentEditpostBinding
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.presentation.home.view.HomeFragmentDirections
import com.example.graduationproject.presentation.newPosts.model.RequestEditPost

class EditPostFragment :Fragment(){
    private lateinit var binding:FragmentEditpostBinding
    private lateinit var   editViewModel:EditPostViewModel
    private lateinit var sharedPreference:SharedPreferenceManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEditpostBinding.inflate(inflater,container,false)
        editViewModel = EditPostViewModel(requireContext())
       sharedPreference  = SharedPreferenceManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view)
            .load(sharedPreference.getProfilePhoto())
            .into(binding.ImageViewProfile)
        binding.profileName.setText(sharedPreference.getProfileName())

        binding.editPost.setOnClickListener {
            var idPost =EditPostFragmentArgs.fromBundle(requireArguments()).idPost
            var caption = binding.tvNewpost.text.toString()
            var token = sharedPreference.getToken()
            var requestEditPost = RequestEditPost(caption,token)
            editViewModel.editPost(requestEditPost,idPost)
            editViewModel.mutableEditResponse.observe(viewLifecycleOwner,{
                if (it.isSuccessful){
                    Toast.makeText(requireContext(),it.body(), Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(),it.body(), Toast.LENGTH_LONG).show()
                }
            }
            )
            findNavController().navigate(EditPostFragmentDirections.actionEditPostFragmentToHomeFragment2())
        }
        binding.cancel.setOnClickListener {
            findNavController().navigate(EditPostFragmentDirections.actionEditPostFragmentToHomeFragment2())
        }

    }

}