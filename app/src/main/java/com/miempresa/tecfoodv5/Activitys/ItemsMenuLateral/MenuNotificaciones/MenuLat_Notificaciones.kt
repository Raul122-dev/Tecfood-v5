package com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuNotificaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuDireccion.AdaptadorMenuDireccionLista
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuDireccion.ModelMenuDireccion
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_menu_lat_direccion.*
import kotlinx.android.synthetic.main.activity_menu_lat_notificaciones.*
import kotlinx.android.synthetic.main.activity_menu_lat_notificaciones.buttonVolverMenu

class MenuLat_Notificaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lat_notificaciones)

        buttonVolverMenu.setOnClickListener(){
            super.onBackPressed();
        }

        listaNotificaciones.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaNotificaciones.layoutManager = LinearLayoutManager(this)

        var llenarListaNotificaciones = ArrayList<ModelMenuNotificacion>()
        for (i in 1 until 20) {
            llenarListaNotificaciones.add(
                ModelMenuNotificacion(
                "Notificacion N°"+i,
                "Descripcion N°"+i,
                "06/"+i,
                "01:"+i+" am"
                )
            )
        }

        val adaptadorNotificacion = AdaptadorMenuNotificacion(llenarListaNotificaciones)
        listaNotificaciones.adapter = adaptadorNotificacion
    }
}