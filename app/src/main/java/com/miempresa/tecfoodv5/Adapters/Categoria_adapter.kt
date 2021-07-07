package com.miempresa.tecfoodv5.Adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.R

class Categoria_adapter(val categoria_rest: ArrayList<String>) : RecyclerView.Adapter<Categoria_adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNombre: TextView = itemView.findViewById(R.id.txt_name_tip)
        val imgTip: ImageView = itemView.findViewById(R.id.img_fondo_tip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Categoria_adapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_explorar, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Categoria_adapter.ViewHolder, position: Int) {
        holder.txtNombre.text = categoria_rest[position]

    }

    override fun getItemCount() = categoria_rest.size
}