package com.example.graduationproject.presentation.lectures.view


import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.graduationproject.R
import com.example.graduationproject.databinding.DialogCommentAddBinding
import com.example.graduationproject.databinding.FragmentLectureCommentsBinding
import com.example.graduationproject.presentation.lectures.adapters.LectureCommentsAdapter
import com.example.graduationproject.presentation.lectures.model.Comment

class LectureCommentsFragment : Fragment() {
    lateinit var binding: FragmentLectureCommentsBinding
    lateinit var lectureCommentViewModel: LectureCommentsViewModel
    lateinit var lectureCommentsAdapter: LectureCommentsAdapter
    private val args: LectureCommentsFragmentArgs by navArgs()
    lateinit var lectureId: String
    lateinit var comment: String
    lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLectureCommentsBinding.inflate(inflater, container, false)
        lectureCommentViewModel = LectureCommentsViewModel(this.requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lectureId = args.lectureId
        getAllComments(lectureId)
        binding.addComment.setOnClickListener {
            //init progress dialog
            initProgressDialog()
            // init dialog to add a comment
            addCommentDialog()

        }

    }

    private fun getAllComments(lectureId: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            lectureCommentViewModel.getAllComments(lectureId)
            lectureCommentViewModel.lectureAllCommentsResponse
                .observe(viewLifecycleOwner, Observer { response ->
                  if(response.isSuccessful)
                      binding.commentsRV.adapter = LectureCommentsAdapter(requireContext(),
                                                          response.body()!!)
                  else{
                      Toast.makeText(requireContext(),
                          response.message(),Toast.LENGTH_SHORT).show()
                  }
                })
        }

    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this.requireContext())
        progressDialog.setTitle("Please wait...")
        progressDialog.setCanceledOnTouchOutside(false)
    }

    private fun addCommentDialog() {
        val commentAddBinding = DialogCommentAddBinding.inflate(
            LayoutInflater.from(this.requireContext()))
        // setup alert dialog
        val builder = AlertDialog.Builder(this.requireContext(),R.style.CustomDialog)
        builder.setView(commentAddBinding.root)
        // create and show alert dialog
        val alertDialog = builder.create()
        alertDialog.show()
        //handle click, dismiss dialog
        commentAddBinding.backBtn.setOnClickListener { alertDialog.dismiss() }

        // handle click, add comment to get data
        commentAddBinding.submitBtn.setOnClickListener {
        comment = commentAddBinding.commentEt.text.toString().trim()
         if(comment.isEmpty()){
             Toast.makeText(this.requireContext(),
                  "Enter comment...",Toast.LENGTH_SHORT).show()
         }else{
             alertDialog.dismiss()
             addComment(comment)

         }
        }

    }

    private fun addComment(comment: String) {
       // show progress
        progressDialog.setMessage("Adding Comment")
        progressDialog.show()

        // setup data to add to retrofit
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            // check if comment is taken already
            Toast.makeText(requireContext(),comment,Toast.LENGTH_SHORT).show()
            // send new comment
            var myComment = Comment(comment)
            lectureCommentViewModel.addComment(lectureId,myComment)
            lectureCommentViewModel.lectureCommentResponse.observe(viewLifecycleOwner
                , Observer { result ->
                    if(result.isSuccessful){
                        Toast.makeText(activity,result.message()
                            ,Toast.LENGTH_SHORT).show()
                     progressDialog.dismiss()
                    }else{
                      Toast.makeText(activity,"Failed",Toast.LENGTH_SHORT).show()
                    }
                    // gat all comments again
                    getAllComments(lectureId)

                })
        }


    }

}