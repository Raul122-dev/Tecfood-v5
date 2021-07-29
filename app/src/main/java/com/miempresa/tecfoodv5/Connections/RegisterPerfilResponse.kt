package com.miempresa.tecfoodv5.Connections

import android.graphics.drawable.Drawable
import kotlinx.serialization.Serializable

@Serializable
data class RegisterPerfilResponse (
    var id:Int = 0,
    var user:Int = 0,
    var distrito:String,
    var plato_fav:String,
    var phone_number:String,
    var picture: Drawable,
)