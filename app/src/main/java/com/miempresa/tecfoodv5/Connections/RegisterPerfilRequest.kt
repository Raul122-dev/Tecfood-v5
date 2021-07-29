package com.miempresa.tecfoodv5.Connections

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.Image
import java.io.File

class RegisterPerfilRequest {
    var user:Int = 0;
    lateinit var distrito:String;
    lateinit var plato_fav:String;
    lateinit var phone_number:String;
    var picture: File? = null;
}