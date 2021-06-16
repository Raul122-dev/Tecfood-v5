package com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuDireccion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miempresa.tecfoodv5.R
import kotlinx.android.synthetic.main.activity_menu_lat_direccion.*

class MenuLat_Direccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_lat_direccion)

        buttonVolverMenu.setOnClickListener(){
            super.onBackPressed();
        }

        listaDirecciones.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaDirecciones.layoutManager = LinearLayoutManager(this)

        var llenarListaDirecciones = ArrayList<ModelMenuDireccion>()
        for (i in 1 until 20) {
            llenarListaDirecciones.add(ModelMenuDireccion("Direccion Especifica N°"+i, "Extra N°"+i))
        }

        val adaptadorDireccion = AdaptadorMenuDireccionLista(llenarListaDirecciones)
        listaDirecciones.adapter = adaptadorDireccion
    }
}