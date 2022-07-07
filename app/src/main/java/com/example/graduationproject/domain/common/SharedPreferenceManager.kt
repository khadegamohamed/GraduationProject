package com.example.graduationproject.domain.common

import android.content.Context

class SharedPreferenceManager (context:Context){
  private val MY_SHARED_PREF_NAME = "my_shared"
  companion object{
    val TOKEN_KEY = "USER_TOKEN"
  }

  val sharedPref = context.getSharedPreferences(MY_SHARED_PREF_NAME,Context.MODE_PRIVATE)
  val editor = sharedPref.edit()

  fun saveToken(tokenVal:String){
    editor.putString(TOKEN_KEY,tokenVal)
    editor.apply()
  }

    fun getToken():String?{
      return sharedPref.getString(TOKEN_KEY,"")
    }



}