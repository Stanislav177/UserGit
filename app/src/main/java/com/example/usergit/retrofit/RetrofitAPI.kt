package com.example.usergit.retrofit

import com.example.usergit.const.URL_API_BASE
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitAPI {

    companion object {
        fun getStartRetrofitAPI(): RequestAPI {
            val retrofit = Retrofit.Builder().baseUrl(URL_API_BASE)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient()
                    .create())).build()
            return retrofit.create(RequestAPI::class.java)
        }
    }
}