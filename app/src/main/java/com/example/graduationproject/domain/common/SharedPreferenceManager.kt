package com.example.graduationproject.domain.common

import android.content.Context

class SharedPreferenceManager (context:Context){
  private val MY_SHARED_PREF_NAME = "my_shared"
  companion object{
    val TOKEN_KEY = "USER_TOKEN"
    val profile_name = "ProfileName"
    val profile_photo="Profile_Photo"
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

  fun saveProfileName(profileNameVal:String){
    editor.putString(profile_name,profileNameVal)
    editor.apply()
  }

  fun getProfileName():String?{
    return sharedPref.getString(profile_name,"")
  }

  fun saveProfilePhoto(profilePhotoVal:String){
    editor.putString(profile_photo,profilePhotoVal)
    editor.apply()
  }

  fun getProfilePhoto():String?{
    return sharedPref.getString(profile_photo,"")
  }




}