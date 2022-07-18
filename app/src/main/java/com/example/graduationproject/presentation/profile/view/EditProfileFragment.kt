package com.example.graduationproject.presentation.profile.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.databinding.FragmentEditProfileBinding
import com.example.graduationproject.domain.common.RealPathUtil
import com.example.graduationproject.domain.common.SharedPreferenceManager
import com.example.graduationproject.presentation.profile.model.Profile
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.util.jar.Manifest
import android.app.Activity.RESULT_OK as act


class EditProfileFragment : Fragment() {
    lateinit var binding: FragmentEditProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    lateinit var sharedPref: SharedPreferenceManager
    lateinit var body: MultipartBody.Part
     var imagePath: String? = null
    lateinit var id: String
    private var fileUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        profileViewModel = ProfileViewModel(this.requireContext())
        sharedPref = SharedPreferenceManager(this.requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id = sharedPref.getUserId().toString()
        // update image
        binding.profileIv.setOnClickListener {
            if(Build.VERSION.SDK_INT >= 22){
                checkAndRequestForPermission()
            }else{
                launchGallery()
            }

        }

        binding.updateBtn.setOnClickListener { v ->
            if (binding.profileNameEt.text.isEmpty())
                Toast.makeText(this.requireContext(),
                    "Enter name please!", Toast.LENGTH_SHORT).show()
            else{

                var name = binding.profileNameEt.text.toString()
                var profileName = Profile(name)
                updateProfileName(id, profileName)
             //   findNavController().navigate(EditProfileFragmentDirections
                   // .actionEditProfileFragment2ToProfileFragment())
             findNavController().popBackStack()

            }

        }

    }

    private fun checkAndRequestForPermission() {
        if(ContextCompat.checkSelfPermission(this.requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                  != PackageManager.PERMISSION_GRANTED){
           if(ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),
               android.Manifest.permission.READ_EXTERNAL_STORAGE)){
               Toast.makeText(this.requireContext(),
                   "Please accept for required permission",Toast.LENGTH_SHORT).show()
           }
            else{
              ActivityCompat.requestPermissions(this.requireActivity(),
              arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), PRequest)
           }
        }else{
            launchGallery()
        }

    }

    private fun launchGallery() {

      val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
          galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode,data)

        if(resultCode == Activity.RESULT_OK &&
                        requestCode== REQUEST_PICK_PHOTO){

            binding.profileIv.setImageURI(data?.data)
            fileUri = data?.data

        //   imagePath = RealPathUtil.getRealPath(this.requireContext(), fileUri!!)
             imagePath = getRealPathFromUri(this.requireContext(),fileUri)
             var file = File(imagePath)
            // convert uri image to file and send it to server
            var requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
             body = MultipartBody.Part.createFormData("file", file.name, requestFile)

            //body = getImageAsMultipartBodyPart(this.requireContext(), fileUri!!,"file")
            Log.d("file",body.toString())
            updateProfileImage(id,body)

        }
    }

    fun getImageAsMultipartBodyPart(context: Context?, uri: Uri,
                                         name: String): MultipartBody.Part {
        val path: String = RealPathUtil.getRealPath(this.requireContext(), uri)!!
        val file = File(path)
        Toast.makeText(context,file.toString(),Toast.LENGTH_SHORT).show()
        val reqFileSelect = file.asRequestBody("image/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, file.name, reqFileSelect)
    }




    // send new name to the server
    private fun updateProfileName(id: String, profileName: Profile) {
        profileViewModel.updateProfileName(id, profileName)
        profileViewModel.updateNameResponse.observe(viewLifecycleOwner,
            Observer { response ->
                if (response.isSuccessful) {
                    Toast.makeText(this.requireContext(),
                       "name updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                 //  Toast.makeText(this.requireContext(),
                   //     response.message(), Toast.LENGTH_SHORT).show()
                    Toast.makeText(this.requireContext(),
                        "Failed", Toast.LENGTH_SHORT).show()
                }
            })
    }

    // send new image to the server
    private fun updateProfileImage(id: String, imageFile: MultipartBody.Part) {
        profileViewModel.updateProfileImage(id, imageFile)
        profileViewModel.updateImageResponse.observe(viewLifecycleOwner,
            Observer { response ->
                if (response.isSuccessful) {
                    Toast.makeText(this.requireContext(), response.body()!!.message
                                   , Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this.requireContext(),
                        response.message(), Toast.LENGTH_SHORT).show()
                    Log.d("edit",response.message())
                }

            })
    }

    fun getRealPathFromUri(context: Context, contentUri: Uri?): String? {
        var cursor: Cursor? = null
        return try {

            val proj =
                arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.getContentResolver()
                .query(contentUri!!, proj, null, null, null)
            val column_index: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor.close()
            }
        }
    }

  companion object{
      const val REQUEST_PICK_PHOTO = 1001
      const val PRequest = 1001

  }



}
