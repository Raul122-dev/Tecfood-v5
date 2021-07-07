package com.miempresa.tecfoodv5.Models

data class Menus(
    val id: Int,
    val restaurante: Int,
    val dia: String,
    val entradas: Array<String>,
    val menus: Array<String>,
    val bebidas: Array<String>,
    val total: String,
    val prec_Almuerzo: String)
