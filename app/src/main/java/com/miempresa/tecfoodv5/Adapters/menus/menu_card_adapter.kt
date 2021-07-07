package com.miempresa.tecfoodv5.Adapters.menus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miempresa.tecfoodv5.R

class menu_card_adapter(val menus: Array<String>) : RecyclerView.Adapter<menu_card_adapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt: TextView = itemView.findViewById(R.id.txt_menu_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): menu_card_adapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: menu_card_adapter.ViewHolder, position: Int) {
        holder.txt.text = menus[position]
    }

    override fun getItemCount() = menus.size

}