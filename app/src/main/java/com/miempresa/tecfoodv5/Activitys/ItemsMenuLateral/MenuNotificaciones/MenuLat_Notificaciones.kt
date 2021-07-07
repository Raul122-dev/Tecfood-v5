package com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuNotificaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_menu_lat_notificaciones.*

class MenuLat_Notificaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lat_notificaciones)

        buttonVolverMenu.setOnClickListener(){
            super.onBackPressed();
        }
    }
}