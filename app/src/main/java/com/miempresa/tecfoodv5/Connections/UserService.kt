package com.miempresa.tecfoodv5.Connections;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface UserService {
    @POST("api/login/")
    fun loginUser(@Body loginRequest:LoginRequest):Call<LoginResponse>;

    @POST("api/register/")
    fun registerUsers(@Body registerRequest:RegisterRequest):Call<RegisterResponse>;

    @POST("74054946816/api/Perfil/")
    fun registerPosts(@Body registerPerfilRequest:RegisterPerfilRequest):Call<RegisterPerfilRequest>;

}
