package com.example.graduationproject.domain.networking

import android.annotation.SuppressLint
import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

class RetrofitBuilder() {

    companion object {
        const val BASE_URL: String = "https://nodejs-college.herokuapp.com/"

        fun getAPIService(context: Context): ServiceAPI {
             val logger = HttpLoggingInterceptor()
                          .setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context))
                .addInterceptor(logger)
                .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
               return retrofit.create(ServiceAPI::class.java)

        }
    }






}