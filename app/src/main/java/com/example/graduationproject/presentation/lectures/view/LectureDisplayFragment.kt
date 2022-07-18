package com.example.graduationproject.presentation.lectures.view

import android.annotation.SuppressLint
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
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentLectureDisplayBinding
import com.example.graduationproject.domain.model.Lecture
import com.example.graduationproject.presentation.lectures.view.uploadLectures.PdfFragment
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import com.nbsp.materialfilepicker.MaterialFilePicker
import java.io.File
import java.util.regex.Pattern

private const val TAG = "LectureDisplayFragment"

class LectureDisplayFragment : Fragment() {

    lateinit var pdfFileName: String
    lateinit var webView: WebView
    lateinit var binding: FragmentLectureDisplayBinding
    private val args by navArgs<LectureDisplayFragmentArgs>()
    private lateinit var lecture: Lecture

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLectureDisplayBinding
            .inflate(layoutInflater, container, false)
            webView = binding.webView
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lecture = args.lecture
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls= true
        webView.settings.displayZoomControls= false
        webView.settings.useWideViewPort = true
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=${lecture.file}")

    }


}
