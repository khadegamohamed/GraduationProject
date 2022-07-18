package com.example.graduationproject.presentation.lectures.view

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.databinding.FragmentLecturesBinding
//import com.example.graduationproject.presentation.auth.LoginFragmentDirections
//import com.example.graduationproject.presentation.lectures.adapters.LecturesAdapter
import kotlinx.coroutines.flow.collect
import java.io.File

private const val TAG = "LecturesFragment"
class LecturesFragment : Fragment(), LecturesAdapter.OnListItemClick {
    private lateinit var  binding : FragmentLecturesBinding
    private lateinit var lecturesViewModel: LecturesViewModel
    private lateinit  var lectureAdapter: LecturesAdapter
    private  var fileUri: Uri = Uri.parse("content://com.android.providers.media.documents/document/document%3A219765")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLecturesBinding.inflate(layoutInflater,container,false)
        lecturesViewModel = LecturesViewModel()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            lecturesViewModel.getLectures().collect {
                lectureAdapter = LecturesAdapter(it)
                binding.lecturesRv.adapter = lectureAdapter
                lectureAdapter.notifyDataSetChanged()

                lectureAdapter.onListItemClick = this@LecturesFragment

            }
        }


        binding.addLectureFab.setOnClickListener {
            openFile()
        }




        return binding.root
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
        }
        startActivityForResult(intent, LectureDisplayFragment.PICK_PDF_FILE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PICK_PDF_FILE -> if (resultCode == RESULT_OK){
                val uri:Uri = data?.data!!
                val uriString:String = uri.toString()
                lateinit var  pdfName: String
                var pdfFile: File? = null
                if(uriString.startsWith("content://")){
                    var myCursor:Cursor? = null
                    try {
                        myCursor = activity?.applicationContext!!
                            .contentResolver.query(uri,null,
                                null,null,null)
                        // to create file from uri
                        pdfFile = File(uri.path)
                        // I want to send file uri to display fragment to open it
                        if(resultCode == RESULT_OK){
                            fileUri = data.data!!
                            Log.d(TAG, "file uri : $fileUri")
                        }

                        if(myCursor != null && myCursor.moveToFirst()){
                            pdfName = myCursor.getString(myCursor
                                .getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        }
                        // how to change id for each lecture
                        lecturesViewModel.addLecture(pdfName,pdfFile)
                        Toast.makeText(context,pdfName,Toast.LENGTH_LONG).show()

                    }finally {
                        myCursor?.close()
                    }

                }
            }
        }
    }

    override fun onItemClick() {
        findNavController().navigate(LecturesFragmentDirections.actionLecturesFragmentToLectureDisplayFragment())
    }

    companion object {
        const val PICK_PDF_FILE = 2
    }

}