package com.miempresa.tecfoodv5.Adapters

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class Restaurante_user(val restaurantes: ArrayList<Restaurante>) : RecyclerView.Adapter<Restaurante_user.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgRest: ImageView = itemView.findViewById(R.id.img_rest)
        val txtNombre: TextView = itemView.findViewById(R.id.txt_name_rest)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurante_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtNombre.text = restaurantes[position].nombre_rest
        Picasso.get()
            .load(restaurantes[position].vista)
            .transform(CropSquareTransformation())
            //.transform(RoundedCornersTransformation(120,0))
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgRest)

        holder.itemView.setOnClickListener(){
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

class CropSquareTransformation : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val result = Bitmap.createBitmap(source, x, y, size, size)
        if (result != source) {
            source.recycle()
        }
        return result
    }

    override fun key(): String {
        return "square()"
    }
}
