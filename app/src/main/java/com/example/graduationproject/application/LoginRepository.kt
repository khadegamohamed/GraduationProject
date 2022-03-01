package com.example.graduationproject.application

import com.google.android.material.textfield.TextInputEditText

class LoginRepository {
    private lateinit var userName:String
    private lateinit var userCode:String
    fun signIn(userNameEt: TextInputEditText, userCodeEt: TextInputEditText): Boolean {

         userName =  userNameEt.text.toString()
         userCode =  userCodeEt.text.toString()

        return validation(userNameEt,userCodeEt)

    }

    private fun validation(userNameEt: TextInputEditText,
                           userCodeEt: TextInputEditText):Boolean{
        when{
            userName.isEmpty() ->{
                userNameEt.error = "Please enter the user name"
                return false
            }
           userCode.isEmpty() ->{
               userCodeEt.error = "Please enter the user code"
               return false
           }

        }
        return true
    }


}