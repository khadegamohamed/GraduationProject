package com.example.graduationproject.presentation.lectures.view.uploadLectures

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationproject.databinding.FragmentPDFBinding
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener


class PdfFragment : Fragment(), OnPageChangeListener, OnLoadCompleteListener,
    OnPageErrorListener {
 lateinit var binding: FragmentPDFBinding
 lateinit var pdfFileName:String
 lateinit var pdfView: PDFView
 lateinit var dialog : ProgressDialog
 lateinit var pdfPath: String
 companion object{
      const val FILE_PICKER_REQUEST_CODE = 1
 }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        binding = FragmentPDFBinding.inflate(layoutInflater, container, false)
        pdfView = binding.pdfview
        initDialog()
        return binding.root
    }

    private fun initDialog() {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onPageChanged(page: Int, pageCount: Int) {
        TODO("Not yet implemented")
    }

    override fun loadComplete(nbPages: Int) {
        TODO("Not yet implemented")
    }

    override fun onPageError(page: Int, t: Throwable?) {
        TODO("Not yet implemented")
    }


}