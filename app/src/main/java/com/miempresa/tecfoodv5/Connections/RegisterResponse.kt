package com.miempresa.tecfoodv5.Connections

import kotlinx.serialization.Serializable


@Serializable
data class RegisterResponse(
    val user: User,
    val token:String,
) {
    data class User(
        val id: Int,
        val username: String,
        val email: String,
    )

}