package com.example.graduationproject.presentation.newPosts.view

import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.graduationproject.databinding.FragmentNewpostBinding
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.presentation.newPosts.model.RequestNewPost
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI
import java.net.URI.create


class NewPostFragment:Fragment() {
    private lateinit var binding: FragmentNewpostBinding
    private lateinit var newPostViewModel: NewPostViewModel
    private lateinit var sharedPrafrence: SharedPreferenceManager
    private lateinit var theImage :Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewpostBinding.inflate(inflater, container, false)
       newPostViewModel = NewPostViewModel(requireContext())
        sharedPrafrence = SharedPreferenceManager(requireContext())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(view)
            .load(sharedPrafrence.getProfilePhoto())
            .into(binding.ImageViewProfile)
     binding.profileName.setText(sharedPrafrence.getProfileName())

        binding.addPost.setOnClickListener {
            if (binding.ImagePost.drawable != null){
                val file: File = File(theImage.getPath())
                val image = RequestBody.create("image/*".toMediaTypeOrNull(), file)
             // val caption = RequestBody.create("text/plain".toMediaTypeOrNull(), binding.tvNewpost.text.toString())
                var caption = binding.tvNewpost.text.toString()
                val body : MultipartBody.Part= MultipartBody.Part.createFormData("file", file.name, image)
               newPostViewModel.addNewPostWithMedia(caption,body)
                newPostViewModel.mutableNewPostWithMediaResponse.observe(viewLifecycleOwner,{
                    if(it.isSuccessful){
                        Toast.makeText(requireContext(),"Sharing Succeeded", Toast.LENGTH_LONG).show()
                        Log.d("NEWPOST", "onViewCreated: ${it.body()!!.caption}")
                    }else{
                        Toast.makeText(requireContext(),"Sharing Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }else{
                var caption = binding.tvNewpost.text.toString()
                val newPost = RequestNewPost(sharedPrafrence.getToken(),caption)
                newPostViewModel.addNewPost(newPost)
                newPostViewModel.mutableNewPostResponse.observe(viewLifecycleOwner,{
                    if(it.isSuccessful){
                        Toast.makeText(requireContext(),"Sharing Succeeded", Toast.LENGTH_LONG).show()
                        Log.d("NEWPOST", "onViewCreated: ${it.body()!!.caption}")
                    }else{
                        Toast.makeText(requireContext(),"Sharing Failed", Toast.LENGTH_LONG).show()
                    }
                })

            }
            findNavController().navigate(NewPostFragmentDirections.actionNewPostFragmentToHomeFragment2())
        }
        binding.cancelPost.setOnClickListener {
            findNavController().navigate(NewPostFragmentDirections.actionNewPostFragmentToHomeFragment2())
        }





    }




}

/*var arl = registerForActivityResult(
    ActivityResultContracts.GetContent()
) { result ->
    theImage = result
    Glide.with(view)
        .load(result)
        .into(binding.ImagePost)
}

binding.addPhotoAndVideo.setOnClickListener {
    Toast.makeText(requireContext(),"The maximum Number Of Photos is one",Toast.LENGTH_LONG).show()
    Toast.makeText(requireContext(),"Be careful images can not be edit after added",Toast.LENGTH_LONG).show()
    arl.launch("image/*")
}*/