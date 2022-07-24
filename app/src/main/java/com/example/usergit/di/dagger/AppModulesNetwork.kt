package com.example.usergit.di.dagger

import com.example.usergit.data.retrofit.RequestAPI
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModulesNetwork {

    private val url = "https://api.github.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRequestAPI(retrofit: Retrofit): RequestAPI {
        return retrofit.create(RequestAPI::class.java)
    }
}