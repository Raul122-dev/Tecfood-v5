package com.miempresa.tecfoodv5.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.miempresa.tecfoodv5.Connections.ApiService
import com.miempresa.tecfoodv5.Connections.Api_Restaurantes
import com.miempresa.tecfoodv5.Models.Restaurante
import com.miempresa.tecfoodv5.R
import com.miempresa.tecfoodv5.fragmentos.explorar
import com.miempresa.tecfoodv5.fragmentos.home
import com.miempresa.tecfoodv5.fragmentos.listas
import com.miempresa.tecfoodv5.fragmentos.preferencias
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var blurView : BlurView;
    lateinit var service: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        val navigationView: NavigationView = findViewById(R.id.menu_navegacion)
        navigationView.setNavigationItemSelectedListener(this)
        //
        val bottomNavigationView : BottomNavigationView = findViewById(R.id.botton_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        //blur interactivo
        //blurView = blur_fondo
        //blurBackground()

        fragmentShow(home())

        //Abrir Menu
        btn_menu.setOnClickListener(){
            navegacion.openDrawer(GravityCompat.START)
            //blur_fondo.startBlur()
            //blur_fondo.blurRadius = 1
        }
        // Iniciacion de retrofit
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://www.tecfood.club/74054946816/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<ApiService>(ApiService::class.java)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            //opciones tab_menu
            R.id.menu_home -> {
                fragmentShow(home())
            }
            R.id.Listas -> {
                fragmentShow(listas())
            }
            R.id.Explorar -> {
                fragmentShow(explorar())
            }
            R.id.Mas -> {

            }
            R.id.Preferencias -> {
                fragmentShow(preferencias())
            }

            // opciones menu lateral
            R.id.invitacion -> {
                // modificado Toast.makeText(this, "elegiste menu invitacion", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "elegiste menu invitacion", Toast.LENGTH_LONG).show()
            }
            R.id.premiun -> {
                Toast.makeText(this, "elegiste menu premiun", Toast.LENGTH_LONG).show()
            }
        }
        return true
    }

    fun fragmentShow(fragment: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in_left, R.anim.slide_out_right
        )
            .replace(R.id.Page_main, fragment).setTransition(
            FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

    private fun blurBackground() {
        val radius = 2f
        val decorView: View = window.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        blurView.setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
    }

    fun getAllRestaurants(){
        service.getAllRestaurants().enqueue(object: Callback<List<Restaurante>> {
            override fun onFailure(call: Call<List<Restaurante>>?, t: Throwable?){
                t?.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<Restaurante>>?,
                response: retrofit2.Response<List<Restaurante>>?
            ) {
                val restaurants = response?.body()
                Log.i("response", Gson().toJson(restaurants))
            }
        })


    }
}