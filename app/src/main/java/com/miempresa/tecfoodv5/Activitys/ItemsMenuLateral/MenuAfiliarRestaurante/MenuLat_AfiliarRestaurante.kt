package com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuAfiliarRestaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_menu_lat_afiliar_restaurante.*

class MenuLat_AfiliarRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lat_afiliar_restaurante)

        buttonVolverMenu.setOnClickListener(){
            super.onBackPressed();
        }
    }
}