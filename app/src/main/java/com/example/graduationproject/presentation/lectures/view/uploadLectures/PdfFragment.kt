package com.example.graduationproject.presentation.lectures.view.uploadLectures

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
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
import com.shockwave.pdfium.PdfDocument
import com.shockwave.pdfium.PdfDocument.Bookmark
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody



class PdfFragment : Fragment(), OnPageChangeListener, OnLoadCompleteListener,
    OnPageErrorListener {
    lateinit var binding: FragmentPDFBinding
    var pdfFileName: String? = null
    lateinit var pdfView: PDFView
    lateinit var dialog: ProgressDialog
    lateinit var pdfPath: String
    private var pageNumber = 0

    companion object {
        const val FILE_PICKER_REQUEST_CODE = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        binding = FragmentPDFBinding.inflate(layoutInflater, container, false)

        pdfView = binding.pdfview

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialog()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.lecture_options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pickFile -> {
                launchPicker()
                return true
            }
            R.id.upload -> {
                uploadFile()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //send it to the server
    private fun uploadFile() {
        TODO("Not yet implemented")
    }

    private fun launchPicker() = MaterialFilePicker()
        .withActivity(this.activity)
        .withRequestCode(FILE_PICKER_REQUEST_CODE)
        .withHiddenFiles(true)
        .withFilter(Pattern.compile(".*\\.pdf$"))
        .withTitle("Select PDF file")
        .start()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            val path = data!!.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)
            val file = File(path)
            displayFromFile(file)
            if (path != null) {
                Log.d("Path: ", path);
                pdfPath = path;
                Toast.makeText(this.requireContext(), "Picked file: $path",
                    Toast.LENGTH_LONG).show()

            }

        }
    }

    private fun displayFromFile(file: File) {
        val uri = Uri.fromFile(File(file.absolutePath))
        pdfFileName = getFileName(uri)

        pdfView.fromFile(file)
            .defaultPage(pageNumber)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle(DefaultScrollHandle(this.requireContext()))
            .spacing(10) // in dp
            .onPageError(this)
            .load();
    }

    @SuppressLint("Range")
    private fun getFileName(uri: Uri?): String? {
        var result: String? = null
        if (uri!!.scheme.equals("content")) {
            val cursor: Cursor? = activity?.applicationContext!!
                .contentResolver.query(uri, null,
                    null, null, null)
            cursor.use { cursor ->
                if (cursor != null && cursor.moveToFirst())
                    result = cursor.getString(cursor
                        .getColumnIndex(OpenableColumns.DISPLAY_NAME))
            }
        }
        if (result == null) {
            result = uri.lastPathSegment
        }
        return result
    }



    override fun loadComplete(nbPages: Int) {
        var mate = pdfView.documentMeta
        printBookmarksTree(pdfView.tableOfContents, "-")

    }

    fun printBookmarksTree(tree: List<Bookmark>, sep: String) {
        for (b in tree) {

            if (b.hasChildren()) {
                printBookmarksTree(b.children, "$sep-")
            }
        }
    }

    override fun onPageError(page: Int, t: Throwable?) {

    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        pageNumber = page;
       //setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }
   private fun uploadPdfFile(){
       if (pdfPath == null){
           Toast.makeText(this.requireContext(),
               "please select a pdf file ", Toast.LENGTH_LONG).show()
       }else{
           showDialog()

           // Map is used to multipart the file using okhttp3.RequestBody
           val map: MutableMap<String, RequestBody> = HashMap()
           val file = File(pdfPath)

           // Parsing any Media type file
           val requestBody: RequestBody = RequestBody
               .create("application/pdf".toMediaTypeOrNull(), file)
               map["file\"; filename=\"" + file.name.toString() + "\""] = requestBody

           // this map will send to the request fun


       }
   }


    private fun initDialog() {
        dialog = ProgressDialog(this.requireContext())
        dialog.setMessage("Loading...")
        dialog.setCancelable(true)
    }

     private fun showDialog() {
        if (!dialog.isShowing) dialog.show()
    }

    private fun hideDialog() {
        if (dialog.isShowing) dialog.dismiss()
    }
}