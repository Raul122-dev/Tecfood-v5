package com.miempresa.tecfoodv5.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuAfiliarRestaurante.MenuLat_AfiliarRestaurante
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuDireccion.MenuLat_Direccion
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuInfoTecfood.MenuLat_InfoTecfood
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuInviteFriends.MenuLat_InvitaAmigos
import com.miempresa.tecfoodv5.Activitys.ItemsMenuLateral.MenuNotificaciones.MenuLat_Notificaciones
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
import kotlinx.android.synthetic.main.header_menu_lateral.*
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

            btnInviteFriends.setOnClickListener(){
                Toast.makeText(this, "Link de invitacion copiado", Toast.LENGTH_LONG).show()
                navegacion.closeDrawer(GravityCompat.START)
            }

            btn_menu_hacerse_premium.setOnClickListener(View.OnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.Page_main, preferencias())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()

                navegacion.closeDrawer(GravityCompat.START)
            })
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
            //borramos invitacion y hacerse premium
            R.id.menuDireccion -> {
                val nextActivity = Intent(applicationContext, MenuLat_Direccion::class.java)
                startActivity(nextActivity)
            }
            R.id.menuMisNotificaciones -> {
                val nextActivity = Intent(applicationContext, MenuLat_Notificaciones::class.java)
                startActivity(nextActivity)
            }
            R.id.menuAfiliarRestaurante -> {
                val nextActivity = Intent(applicationContext, MenuLat_AfiliarRestaurante::class.java)
                startActivity(nextActivity)
            }
            R.id.menuAcercaDeTecFood -> {
                val nextActivity = Intent(applicationContext, MenuLat_InfoTecfood::class.java)
                startActivity(nextActivity)
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