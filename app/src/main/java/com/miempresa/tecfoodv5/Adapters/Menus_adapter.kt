package com.miempresa.tecfoodv5.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.Models.Menus
import com.miempresa.tecfoodv5.R
import com.squareup.picasso.Picasso

class menus_adapter(val menus:ArrayList<Menus>):RecyclerView.Adapter<menus_adapter.viewHolder>(){

    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtTitulo: TextView = itemView.findViewById(R.id.t1)
        val txtRestaurante: TextView = itemView.findViewById(R.id.te1)
        val txtPrecio: TextView = itemView.findViewById(R.id.precio)
        val img_del: ImageView = itemView.findViewById(R.id.img_delivery)
        val img_plato: ImageView = itemView.findViewById(R.id.img_plato)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        /*
        holder.txtTitulo.text = menus[position].nombre
        holder.txtRestaurante.text = menus[position].restaurant
        holder.txtPrecio.text = menus[position].precio.toString()
        Picasso.get().load(menus[position].img_delivery).error(R.drawable.ic_launcher_background).into(holder.img_del)
        Picasso.get().load(menus[position].img_plato).error(R.drawable.ic_launcher_background).into(holder.img_plato)*/

    }

    override fun getItemCount() = menus.size

}