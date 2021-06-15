package com.miempresa.tecfoodv5.Models

data class  Restaurante(
    val id:Int,
    val usuario:Int,
    val DNI: String,
    val nombrerest:String,
    val ubicacion:String,
    val direccion:String,
    val descripcion:String,
    val contacto:String,
    val puntuacion:String,
    val latitud:String,
    val longitud:String,
    val creado:String,
    val vista:String)