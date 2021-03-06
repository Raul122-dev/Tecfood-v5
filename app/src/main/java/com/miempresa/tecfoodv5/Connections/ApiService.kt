package com.miempresa.tecfoodv5.Connections

import com.miempresa.tecfoodv5.Models.Menus
import com.miempresa.tecfoodv5.Models.Perfil
import com.miempresa.tecfoodv5.Models.Restaurante
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //Obtener todos los restaurante
    @GET("Restaurante/")
    fun getAllRestaurants(): Call<List<Restaurante>>

    //Obtener Restaurante Unico
    @GET("Restaurante/{id}")
    fun getRestaurantById(@Path("id") id: Int): Call<Restaurante>

    //Obtener todos los menus
    @GET("Menus")
    fun getAllMenus(): Call<List<Menus>>

    //Obtener Menus de Restaurante
    @GET("Menus")
    fun getMenusOfRestaurantById(@Query("restaurante") idRest: Int): Call<Menus>

    //Obtener Perfil_User
    @GET("Perfil/{id}")
    fun getPerfilUser(@Path("id") id: Int): Call<Perfil>

    @POST("Perfil/")
    fun registerPosts(@Body registerPerfilRequest:RegisterPerfilRequest):Call<RegisterPerfilResponse>;

}