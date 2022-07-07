package com.example.graduationproject.domain.networking


import android.content.Context
import android.content.SharedPreferences
import com.example.graduationproject.domain.common.SharedPreferenceManager
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class AuthInterceptor(context: Context): Interceptor {
  val sharedPref = SharedPreferenceManager(context)


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer ${sharedPref.getToken().toString()}")
            .build()
        return chain.proceed(request)
    }
}