package com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuDireccion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.R

class AdaptadorMenuDireccionLista (val listaDireccionesModelMenus: ArrayList<ModelMenuDireccion>): RecyclerView.Adapter<AdaptadorMenuDireccionLista.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dEspecifica = itemView.findViewById<TextView>(R.id.idDireccionEspecifica)
        val dExtra = itemView.findViewById<TextView>(R.id.idDireccionExtra)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val items = LayoutInflater.from(parent?.context).inflate(R.layout.item_direcciones_cardview, parent, false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.dEspecifica?.text = listaDireccionesModelMenus[position].direccionEspecifica
        holder?.dExtra?.text = listaDireccionesModelMenus[position].direccionExtra
    }

    override fun getItemCount(): Int {
        return listaDireccionesModelMenus.size
    }

}