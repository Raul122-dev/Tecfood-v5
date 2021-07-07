package com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuInfoTecfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_menu_lat_info_tecfood.*

class MenuLat_InfoTecfood : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lat_info_tecfood)

        buttonVolverMenu.setOnClickListener(){
            super.onBackPressed();
        }
    }
}