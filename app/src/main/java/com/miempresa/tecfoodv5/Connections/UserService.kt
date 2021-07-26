package com.miempresa.tecfoodv5.Connections;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface UserService {

    @POST("login/")
    fun loginUser(@Body loginRequest:LoginRequest):Call<LoginResponse>;
}
