package com.example.graduationproject.domain.common

import android.content.Context

// class for local storage
class SharedPreferenceManager (context:Context){
  private val MY_SHARED_PREF_NAME = "my_shared"
  companion object{

    val profile_name = "ProfileName"
    val profile_photo="Profile_Photo"

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

//  fun saveProfileName(newName:String){
//    editor.putString(PROFILE_NAME,newName)
//  }
//  fun getProfileName():String?{
//    return sharedPref.getString(PROFILE_NAME,"")
//  }
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