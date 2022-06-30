package com.example.graduationproject.presentation.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graduationproject.application.LoginRepository
import com.example.graduationproject.domain.model.LoginResponse
import com.example.graduationproject.domain.model.User
import com.example.graduationproject.domain.networking.RetrofitBuilder
import com.example.graduationproject.domain.networking.ServiceAPI
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(context: Context) : ViewModel() {
   val context = context
   var loginResponse : MutableLiveData<Response<LoginResponse>> = MutableLiveData()
    var serviceInst = RetrofitBuilder.getRetrofitClientInstance()
         .create(ServiceAPI::class.java)
    private var loginRepo = LoginRepository(serviceInst)

    fun signIn(userNameEt: TextInputEditText, userCodeEt: TextInputEditText) =
        loginRepo.signIn(userNameEt, userCodeEt)

    fun login(user: User) {
       viewModelScope.launch {
           val response = loginRepo.login(user)
           loginResponse.value = response

       }
    }

    fun getUserInfo(token: String) = viewModelScope.launch {
        loginRepo.getSecretInfo(token)
    }

}