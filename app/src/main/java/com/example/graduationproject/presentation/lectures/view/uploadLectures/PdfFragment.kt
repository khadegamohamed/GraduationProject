package com.example.graduationproject.presentation.lectures.view.uploadLectures

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentPDFBinding
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.nbsp.materialfilepicker.MaterialFilePicker
import com.nbsp.materialfilepicker.ui.FilePickerActivity
import java.io.File
import java.util.regex.Pattern
import android.provider.OpenableColumns
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.graduationproject.domain.common.RealPathUtil
import com.example.graduationproject.presentation.lectures.view.LecturesViewModel
import com.shockwave.pdfium.PdfDocument
import com.shockwave.pdfium.PdfDocument.Bookmark
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody

private const val TAG ="PdfFragment"
class PdfFragment : Fragment() {
    lateinit var binding: FragmentPDFBinding
    lateinit var lecturesViewModel: LecturesViewModel
    lateinit var pdfView: PDFView
     var pdfUri: Uri? =null
    lateinit var dialog: ProgressDialog
    lateinit var pdfPath: String
    lateinit var part: MultipartBody.Part


    companion object {
        const val FILE_PICKER_REQUEST_CODE = 1
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        binding = FragmentPDFBinding.inflate(inflater, container, false)
        lecturesViewModel = LecturesViewModel(this.requireContext())

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pdfView = binding.pdfview
        binding.pdfFab.setOnClickListener {
          pdfPickIntent()
        }

        binding.uploadFab.setOnClickListener {
            uploadPdfToServer(part)
        }

    }

    private fun pdfPickIntent() {
        val intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        pdfActivityResultLauncher.launch(intent)
    }

    val pdfActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback <ActivityResult>{ result ->
            if(result.resultCode == RESULT_OK){
              pdfUri = result.data!!.data
                showPdfFromUri(pdfUri!!)
                // get file as multi part
             val file = File(pdfUri!!.path)
                val requestBody = RequestBody.create("*/*".toMediaTypeOrNull(), file)
                part = MultipartBody.Part.createFormData("file",file.name,requestBody)
              Toast.makeText(this.requireContext()
                          ," ${file} ",Toast.LENGTH_SHORT).show()

                Log.d(TAG, "File: ${part} ")


            }
            else{
                Toast.makeText(this.requireContext(),
                    "Cancelled", Toast.LENGTH_SHORT).show()
            }

        }
    )

    fun showPdfFromUri(uri: Uri){
        pdfView.fromUri(pdfUri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    fun getFileAsMultipartBodyPart(context: Context?, uri: Uri,
                                    name: String): MultipartBody.Part {
        val path: String = RealPathUtil.getRealPath(this.requireContext(), uri)!!
        val file = File(path)
        Log.d(TAG, "getFileAsMultipartBodyPart: ${file}")
        Toast.makeText(context,file.toString(),Toast.LENGTH_SHORT).show()
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

}