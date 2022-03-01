package com.example.graduationproject.presentation.auth

import androidx.lifecycle.ViewModel
import com.example.graduationproject.application.LoginRepository
import com.google.android.material.textfield.TextInputEditText

class LoginViewModel(): ViewModel() {
    private  var loginRepo= LoginRepository()
    fun signIn(userNameEt: TextInputEditText, userCodeEt: TextInputEditText) =
                                  loginRepo.signIn(userNameEt, userCodeEt)
}