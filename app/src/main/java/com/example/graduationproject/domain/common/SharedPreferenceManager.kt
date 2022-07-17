package com.example.graduationproject.domain.common

import android.content.Context

// class for local storage
class SharedPreferenceManager (context:Context){
  private val MY_SHARED_PREF_NAME = "my_shared"
  companion object{
    const val TOKEN_KEY = "USER_TOKEN"
    const val USER_ID = "USER_ID"
    const val USER_ROLE = "USER_ROLE"
    const val  PROFILE_NAME = "PROFILE_NAME"
  }

  val sharedPref = context.getSharedPreferences(MY_SHARED_PREF_NAME,Context.MODE_PRIVATE)
  val editor = sharedPref.edit()

  fun saveToken(tokenVal:String){
    editor.putString(TOKEN_KEY,tokenVal)
    editor.apply()
  }
  fun saveUserId(idVal:String){
    editor.putString(USER_ID,idVal)
    editor.apply()
  }
  fun saveUserRole(roleVal:String){
    editor.putString(USER_ROLE,roleVal)
    editor.apply()
  }

  fun saveProfileName(newName:String){
    editor.putString(PROFILE_NAME,newName)
  }
  fun getProfileName():String?{
    return sharedPref.getString(PROFILE_NAME,"")
  }
    fun getToken():String?{
      return sharedPref.getString(TOKEN_KEY,"")
    }

   fun getUserId():String?{
     return sharedPref.getString(USER_ID,"")
   }

  fun getUserRole():String?{
    return sharedPref.getString(USER_ROLE,"")
  }

  fun signOut(){
    editor.clear()
    editor.commit()
  }

}