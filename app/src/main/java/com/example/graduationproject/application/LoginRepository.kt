package com.example.graduationproject.application

import com.example.graduationproject.presentation.auth.model.User
import com.example.graduationproject.domain.networking.ServiceAPI
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository (private val api:ServiceAPI){

    fun signIn(userNameEt: TextInputEditText, userCodeEt: TextInputEditText): Boolean {

        return validation(userNameEt,userCodeEt)

    }



    private fun validation(userNameEt: TextInputEditText,
                           userCodeEt: TextInputEditText):Boolean{
        when{
            userNameEt.text.toString().isEmpty() ->{
                userNameEt.error = "Please enter the user nameTV"
                return false
            }
           userCodeEt.text.toString().isEmpty() ->{
               userCodeEt.error = "Please enter the user code"
               return false
           }

        }
        return true
    }

    suspend fun login(user: User) =
        withContext(Dispatchers.IO){
              api.login(user)

        }





}