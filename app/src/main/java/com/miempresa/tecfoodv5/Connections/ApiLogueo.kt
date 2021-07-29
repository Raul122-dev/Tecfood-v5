package com.miempresa.tecfoodv5.Connections

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiLogueo {

    fun getRetrofit():Retrofit{

          val httpLoggingInterceptor =  HttpLoggingInterceptor()
          httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

          val okHttpClient:OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()



          val retrofit:Retrofit = Retrofit.Builder()
              .addConverterFactory(GsonConverterFactory.create())
              .baseUrl("https://www.tecfood.club/")
              .client(okHttpClient)
              .build();

        return retrofit;
    }

    fun getService():UserService{
        val userService:UserService = getRetrofit().create(UserService::class.java)
        return userService;
    }

}