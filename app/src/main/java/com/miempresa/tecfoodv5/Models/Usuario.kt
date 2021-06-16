package com.miempresa.tecfoodv5.Models

data class Usuario (
    val id : Int,
    val contraseña : String,
    val nombre : String,
    val primer_nombre : String,
    val ultimo_nombre : String,
    val email : String)