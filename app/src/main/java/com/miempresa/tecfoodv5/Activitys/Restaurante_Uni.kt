package com.miempresa.tecfoodv5.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.MapFragment
import com.miempresa.tecfoodv5.Adapters.menus.menu_card_adapter
import com.miempresa.tecfoodv5.Connections.ApiService
import com.miempresa.tecfoodv5.Models.Menus
import com.miempresa.tecfoodv5.Models.Restaurante
import com.miempresa.tecfoodv5.R
import com.squareup.picasso.Picasso
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.activity_restaurante_uni.*
import kotlinx.android.synthetic.main.activity_restaurante_uni.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Restaurante_Uni : AppCompatActivity() {

    //lateinit var blurView : BlurView;
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://www.tecfood.club/74054946816/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create<ApiService>(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante_uni)

        var intent = intent
        val id_rest = intent.getStringExtra("id_rest")


        //datos del restaurante
        if (id_rest != null) {
            service.getRestaurantById(id_rest.toInt()).enqueue(object : Callback<Restaurante> {
                override fun onResponse(call: Call<Restaurante>, response: Response<Restaurante>) {
                    val restauranteU = response.body()
                    if (restauranteU != null) {
                        Nombre_Rest.text = restauranteU.nombre_rest
                        info_Rest.text = restauranteU.descripcion
                        location_Rest.text = restauranteU.direccion
                        Picasso.get().load(restauranteU.vista).error(R.drawable.ic_launcher_background).fit().centerCrop().into(Img_Rest)
                        data_menus()
                    }
                }

                override fun onFailure(call: Call<Restaurante>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }

        SV_content_rest.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > 0 || scrollY < 0 && btn_ubication_rest.isShown){
                btn_ubication_rest.hide()
                btn_to_return.visibility = View.INVISIBLE
                blur_view.setBlurRadius(15F)
                blur_view.setOverlayColor(1000)
                //blur interactivo
                //btn_flotation.hide()
            }else {
                //btn_flotation.show()
                btn_to_return.visibility = View.VISIBLE
                btn_ubication_rest.show()
                blur_view.setBlurRadius(0F)
                blur_view.setOverlayColor(0)
            }

            //Log.i("Scrolls: ", "oldScrollX: " + oldScrollX + " ,oldScrollY: "+ oldScrollY + "  " + "ScrollX: " + scrollX + " ,ScrollY: "+ scrollY)

        }

        btn_ubication_rest.setOnClickListener(){
            Toast.makeText(this, "ubicacion presionada", Toast.LENGTH_LONG).show()
        }

        btn_to_return.setOnClickListener(){
            onBackPressed()
        }

        //Mapa de Restaurante
        val mpa_rest = com.miempresa.tecfoodv5.fragmentos.MapFragment()
        fragmentShow(mpa_rest)

    }

    fun data_menus(){
        items_card_menu.layoutManager = LinearLayoutManager(this)
        service.getMenusOfRestaurantById(5).enqueue(object :Callback<Menus>{
            override fun onResponse(call: Call<Menus>, response: Response<Menus>) {
                val menu = response.body()
                if (menu != null){
                    var entradas = menu.entradas
                    //items_card_menu.adapter = menu_card_adapter(entradas)
                    Log.i("menus", entradas.toString())
                }
            }

            override fun onFailure(call: Call<Menus>, t: Throwable) {
                t.printStackTrace()
            }

        })


    }

    fun fragmentShow(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).setTransition(
                FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

}


