package com.miempresa.tecfoodv5.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.R
import com.miempresa.tecfoodv5.Models.Restaurantes
import com.squareup.picasso.Picasso

class restaurant_adapter(val restaurantes:ArrayList<Restaurantes>):RecyclerView.Adapter<restaurant_adapter.viewHolder>() {
    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtNombre_Rest: TextView = itemView.findViewById(R.id.nombre_rest)
        val txtUbicacion: TextView = itemView.findViewById(R.id.ubicacion)
        val txtCalificacion: TextView = itemView.findViewById(R.id.calificacion)
        val img_rest: ImageView = itemView.findViewById(R.id.restaurante_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurante,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.txtNombre_Rest.text = restaurantes[position].nombre_rest
        holder.txtUbicacion.text = restaurantes[position].ubicacion
        holder.txtCalificacion.text = restaurantes[position].calificacion.toString()
        Picasso.get().load(restaurantes[position].img_rest).error(R.drawable.ic_launcher_background).into(holder.img_rest)
    }

    override fun getItemCount() = restaurantes.size

}