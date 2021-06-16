package com.miempresa.tecfoodv5.Connections


import com.miempresa.tecfoodv5.Models.Restaurante

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api_Restaurantes {

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.tecfood.club/74054946816/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create<ApiService>(ApiService::class.java)

    fun getAllRestaurants() {

        var restaurants : ArrayList<Restaurante>

        service.getAllRestaurants().enqueue(object : Callback<List<Restaurante>>{
            override fun onResponse(
                call: Call<List<Restaurante>>,
                response: retrofit2.Response<List<Restaurante>>
            ) {
                val restaurants = response.body()

                //Log.i("mensaje", Gson().toJson(restaurants))
            }

            override fun onFailure(call: Call<List<Restaurante>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

}


