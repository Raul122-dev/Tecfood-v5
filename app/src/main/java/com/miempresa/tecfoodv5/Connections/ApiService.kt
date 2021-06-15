package com.miempresa.tecfoodv5.Connections

import com.miempresa.tecfoodv5.Models.Restaurante
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("Restaurante/")
    fun getAllRestaurants(): Call<List<Restaurante>>

    @GET("Restaurante/{id}")
    fun getRestaurantById(@Path("id") id: Int): Call<Restaurante>

}