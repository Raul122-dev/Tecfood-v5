package com.miempresa.tecfoodv5.Connections;

import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse (
     val id: Int,
     val email: String,
     val username: String,)