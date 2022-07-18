package com.example.usergit.data.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val URL_API_BASE = "https://api.github.com"

interface RetrofitAPI {

    companion object {
        fun getStartRetrofitAPI(): RequestAPI {
            val retrofit = Retrofit.Builder().baseUrl(URL_API_BASE)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient()
                    .create())).build()
            return retrofit.create(RequestAPI::class.java)
        }
    }
}