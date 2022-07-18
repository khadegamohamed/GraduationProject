package com.example.graduationproject.presentation.lectures.view

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.test.core.app.ApplicationProvider
import com.example.graduationproject.databinding.FragmentLecturesBinding
import com.example.graduationproject.presentation.lectures.view.LecturesFragment.Companion.PICK_PDF_FILE
//import com.example.graduationproject.presentation.auth.LoginFragmentDirections
//import com.example.graduationproject.presentation.lectures.adapters.LecturesAdapter
import kotlinx.coroutines.flow.collect
import java.io.File
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.graduationproject.domain.common.RealPathUtil

import com.karumi.dexter.listener.DexterError

import com.karumi.dexter.listener.PermissionRequestErrorListener

import com.karumi.dexter.PermissionToken

import com.karumi.dexter.MultiplePermissionsReport

import com.karumi.dexter.listener.multi.MultiplePermissionsListener

import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionRequest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.util.jar.Manifest


private const val TAG = "LecturesFragment"

class LecturesFragment : Fragment() {
    private lateinit var binding: FragmentLecturesBinding
    private lateinit var lecturesViewModel: LecturesViewModel
    private lateinit var lectureAdapter: LecturesAdapter
    lateinit var subjectId:String
    private val args: LecturesFragmentArgs by navArgs()
    private var fileUri: Uri? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentLecturesBinding.inflate(layoutInflater, container, false)
        lecturesViewModel = LecturesViewModel(this.requireContext())
          subjectId = args.subjectId

        getAllLectures()



        binding.addLectureFab.setOnClickListener {

            openFile()
        }

        return binding.root
    }

    private fun getAllLectures() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            lecturesViewModel.getAllLectures(subjectId)
            lecturesViewModel.lecturesResponse.observe(viewLifecycleOwner,
                Observer {
                    lectureAdapter = LecturesAdapter(it)
                    binding.lecturesRv.adapter = lectureAdapter
                    lectureAdapter.notifyDataSetChanged()
                })


        }
    }


    private fun requestMultiplePermissions() {
        Dexter.withActivity(this.requireActivity())
            .withPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        Toast.makeText(ApplicationProvider.getApplicationContext<Context>(),
                            "All permissions are granted by user!",
                            Toast.LENGTH_SHORT).show()
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // show alert dialog navigating to Settings
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken,
                ) {
                    token.continuePermissionRequest()
                }
            })
            .withErrorListener {
                Toast.makeText(ApplicationProvider.getApplicationContext<Context>(),
                    "Some Error! ",
                    Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()
    }


    @RequiresApi(Build.VERSION_CODES.O)
     fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
        }
        startActivityForResult(intent, PICK_PDF_FILE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            PICK_PDF_FILE -> if (resultCode == RESULT_OK) {
                val uri: Uri = data?.data!!
                val uriString: String = uri.toString()
                lateinit var pdfName: String
                var pdfFile: File? = null
                if (uriString.startsWith("content://")) {
                    var myCursor: Cursor? = null
                    try {
                        myCursor = activity?.applicationContext!!
                            .contentResolver.query(uri, null,
                                null, null, null)
                        // to create file from uri
                        // I want to send file uri to display fragment to open it
                        if (resultCode == RESULT_OK) {
                            fileUri = data.data!!
                            Log.d(TAG, "file uri : $fileUri")
                        }

                        if (myCursor != null && myCursor.moveToFirst()) {
                            pdfName = myCursor.getString(myCursor
                                .getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        }
                    

                       var pdfPath = RealPathUtil.getRealPath(this.requireContext(), fileUri!!)
                        Toast.makeText(this.requireContext(),pdfPath,Toast.LENGTH_SHORT).show()
                        pdfFile = File(fileUri!!.path)

                     Log.d(TAG,"PdfFile: ${pdfFile.toString()}")

                        var part = getFileAsMultipartBodyPart(this.requireContext(),
                            fileUri!!,"file")

                        uploadPdfToServer(part)
                        Toast.makeText(context, pdfName, Toast.LENGTH_LONG).show()

                    } finally {
                        myCursor?.close()
                    }

                }
            }
        }
    }


    fun getFileAsMultipartBodyPart(context: Context?, uri: Uri,
                                    name: String): MultipartBody.Part {
        val path: String = RealPathUtil.getRealPath(this.requireContext(), uri)!!
        val file = File(path)
        val reqFileSelect = file.asRequestBody("*/*".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(name, file.name, reqFileSelect)
    }

    private fun uploadPdfToServer(part: MultipartBody.Part) {
          viewLifecycleOwner.lifecycleScope.launchWhenCreated {
              lecturesViewModel.uploadLecture(part)
              lecturesViewModel.uploadLecturesResponse
                  .observe(viewLifecycleOwner, Observer { response ->
                      if(response.isSuccessful)
                          Toast.makeText(requireContext(),
                              response.body()!!.message,Toast.LENGTH_SHORT).show()
                      else
                          Toast.makeText(requireContext(),
                              response.message(),Toast.LENGTH_SHORT).show()

                  })
          }
    }


    companion object {
        const val PICK_PDF_FILE = 2
    }

}