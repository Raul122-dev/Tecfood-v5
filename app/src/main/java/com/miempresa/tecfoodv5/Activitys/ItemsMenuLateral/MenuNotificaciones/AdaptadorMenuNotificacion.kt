package com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuNotificaciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuDireccion.AdaptadorMenuDireccionLista
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuDireccion.ModelMenuDireccion
import com.miempresa.tecfoodv5.R

class AdaptadorMenuNotificacion (val listaNotificacionesModelMenu: ArrayList<ModelMenuNotificacion>): RecyclerView.Adapter<AdaptadorMenuNotificacion.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notifytitulo = itemView.findViewById<TextView>(R.id.idTituloNotificacion)
        val notifyDescripcion = itemView.findViewById<TextView>(R.id.idDescripcionNotificacion)
        val notifyFecha = itemView.findViewById<TextView>(R.id.fechaNotificacion)
        val notifyHora = itemView.findViewById<TextView>(R.id.horaNotificacion)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val items = LayoutInflater.from(parent?.context).inflate(R.layout.menu_item_notificacion, parent, false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.notifytitulo?.text = listaNotificacionesModelMenu[position].notTitulo
        holder?.notifyDescripcion?.text = listaNotificacionesModelMenu[position].notDescripcion
        holder?.notifyFecha?.text = listaNotificacionesModelMenu[position].notFecha
        holder?.notifyHora?.text = listaNotificacionesModelMenu[position].notHora
    }

    override fun getItemCount(): Int {
        return listaNotificacionesModelMenu.size
    }

}