package com.example.graduationproject.domain.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("users")

class DataStoreManager(val context: Context) {

    val dataStore : DataStore<Preferences> = context.dataStore

       // create keys to store and retrieve values by them
    companion object{
     val USER_TOKEN = stringPreferencesKey("TOKEN")
     val USER_ROLE = stringPreferencesKey("ROLE")
     val USER_ID = stringPreferencesKey("ID")
     val PROFILE_NAME = stringPreferencesKey("PROFILE_NAME")

    }


    // to save data
    suspend fun save(key:String,value:String){
        val dataStoreKey = stringPreferencesKey(key)
         dataStore.edit {  users ->
             users[dataStoreKey] = value

         }
    }
    // to read data
    suspend fun read(key:String): String?{
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}