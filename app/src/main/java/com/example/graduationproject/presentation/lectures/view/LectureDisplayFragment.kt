package com.example.graduationproject.presentation.lectures.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentLectureDisplayBinding

private const val TAG = "LectureDisplayFragment"

class LectureDisplayFragment : Fragment() {
    lateinit var binding: FragmentLectureDisplayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLectureDisplayBinding
            .inflate(layoutInflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openFile()
    }

    private fun showPdfFromUri(uri: Uri) {
        binding.pdfview.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .onError {
                Log.d(TAG, "showPdfFromUri: ${it.localizedMessage}")
            }
            .load()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, Uri.EMPTY)
        }
        startActivityForResult(intent, PICK_PDF_FILE)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        resultData: Intent?
    ) {
        if (requestCode == PICK_PDF_FILE &&
            resultCode == Activity.RESULT_OK
        ) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            resultData?.data?.also { uri ->
                // Perform operations on the document using its URI.
                showPdfFromUri(uri)
            }
        }
    }

    companion object {
        const val PICK_PDF_FILE = 2
    }


}
