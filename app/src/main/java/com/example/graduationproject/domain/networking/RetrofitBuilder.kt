package com.example.graduationproject.domain.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {


    companion object {
        const val BASE_URL: String = "https://nodejs-college.herokuapp.com/"

        fun getRetrofitClientInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}