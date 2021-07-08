package com.miempresa.tecfoodv5.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.Activitys.Restaurante_Uni
import com.miempresa.tecfoodv5.Models.Restaurante
import com.miempresa.tecfoodv5.R
import com.squareup.picasso.Picasso

class restaurant_adapter(val restaurantes:ArrayList<Restaurante>):RecyclerView.Adapter<restaurant_adapter.viewHolder>() {

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
        holder.txtCalificacion.text = restaurantes[position].puntuacion
        Picasso.get().load(restaurantes[position].vista).error(R.drawable.ic_launcher_background).into(holder.img_rest)

        //Para el click enviar vista_unic
        holder.itemView.setOnClickListener() {
            var context = holder.itemView.getContext()
            val model = restaurantes.get(position)
            var id_rest = model.id.toString()
            val intent = Intent(context, Restaurante_Uni::class.java)
            intent.putExtra("id_rest", id_rest)

            context.startActivity(intent)
        }
    }

    override fun getItemCount() = restaurantes.size

}